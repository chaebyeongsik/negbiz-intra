/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbs.support;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import zesinc.core.cache.CacheService;
import zesinc.core.lang.Validate;
import zesinc.intra.bbs.domain.BbsVO;
import zesinc.web.support.BaseConfig;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;
import zesinc.web.validate.annotation.marker.Date;
import zesinc.web.validate.annotation.marker.Email;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.RequireFrom;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.cache.BbsConfigCacheVO;
import zesinc.web.vo.cache.BbsItemCacheVO;

/**
 * 게시판 관리의 설정에 따른 검증기능
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016. 1. 10.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class BbsValidate {

    public static final String BBS_ITEM_CACHE_KEY = BaseConfig.BBS_ITEM_CACHE_KEY;

    /**
     * 게시판 환경 설정에서 지정된 필수 입력 또는 컬럼 데이터 유형에 따른 검증후 오류 결과를 반환
     * 
     * @param configVo
     * @return
     */
    @SuppressWarnings("unchecked")
    public static ValidateResultHolder validate(BbsConfigCacheVO configVo, BbsVO bbsVo) {
        ValidateResultHolder holder = new ValidateResultHolder();

        List<BbsItemCacheVO> bbsItemList = (List<BbsItemCacheVO>) CacheService.get(BBS_ITEM_CACHE_KEY + configVo.getBbsSn());

        for(BbsItemCacheVO itemVo : bbsItemList) {
            String esntlYn = itemVo.getEsntlYn();
            String colId = itemVo.getColId();
            String colTypeNm = itemVo.getColTypeNm();

            if(Validate.isEmpty(colTypeNm)) {
                colTypeNm = "";
            }

            Annotation annotation = null;
            List<Annotation> annotationList = new ArrayList<Annotation>();

            // 필수여부 체크
            if(Validate.isNotEmpty(esntlYn) && esntlYn.equals("Y")) {
                annotation = new Required() {

                    @Override
                    public Class<? extends Annotation> annotationType() {
                        return Required.class;
                    }

                    @Override
                    public String message() {
                        return "required";
                    }
                };
                annotationList.add(annotation);
            }

            // 날짜 포멧 체크
            if(colTypeNm.equals("DATE")) {
                annotation = new Date() {

                    @Override
                    public Class<? extends Annotation> annotationType() {
                        return Date.class;
                    }

                    @Override
                    public String pattern() {
                        return "YYYY-MM-DD";
                    }

                    @Override
                    public String message() {
                        return "date";
                    }
                };
                annotationList.add(annotation);
            }

            // 이메일 포멧 체크
            if(colTypeNm.equals("EMAIL")) {
                annotation = new Email() {

                    @Override
                    public Class<? extends Annotation> annotationType() {
                        return Email.class;
                    }

                    @Override
                    public String message() {
                        return "email";
                    }
                };
                annotationList.add(annotation);
            }

            if(colId.equals("ttl")) {
                // 제목 최대 길이
                annotation = new MaxLength() {

                    @Override
                    public Class<? extends Annotation> annotationType() {
                        return MaxLength.class;
                    }

                    @Override
                    public String message() {
                        return "maxlength";
                    }

                    @Override
                    public int max() {
                        return 500;
                    }
                };
                annotationList.add(annotation);
            } else if(colId.equals("ntcBgngDt") || colId.equals("ntcEndDt")) {
                // 공지의 경우 공지여부 Y에 따라서 필수 요건을 확인한다.
                annotation = new RequireFrom() {

                    @Override
                    public Class<? extends Annotation> annotationType() {
                        return RequireFrom.class;
                    }

                    @Override
                    public String message() {
                        return "requirefrom";
                    }

                    @Override
                    public String fieldValue() {
                        return "Y";
                    }

                    @Override
                    public String fieldName() {
                        return "ntcPstYn";
                    }

                    @Override
                    public String fieldDesc() {
                        return "공지여부 사용";
                    }
                };
                annotationList.add(annotation);
            } else if(colId.startsWith("flctnColCn")) {
                // 확장 컬럼은 DB 사이즈가 2000 이므로 제한
                annotation = new MaxLength() {

                    @Override
                    public Class<? extends Annotation> annotationType() {
                        return MaxLength.class;
                    }

                    @Override
                    public String message() {
                        return "maxlength";
                    }

                    @Override
                    public int max() {
                        return 2000;
                    }
                };
                annotationList.add(annotation);
            } else if(colId.equals("rgtrNm")) {
                // 등록자명을 입력 받을 경우
                annotation = new MaxLength() {

                    @Override
                    public Class<? extends Annotation> annotationType() {
                        return MaxLength.class;
                    }

                    @Override
                    public String message() {
                        return "maxlength";
                    }

                    @Override
                    public int max() {
                        return 100;
                    }
                };
                annotationList.add(annotation);
            }

            ValidateUtil.doFiledValidate(holder, bbsVo, colId, annotationList.toArray(new Annotation[annotationList.size()]));
        }

        return holder;
    }
}

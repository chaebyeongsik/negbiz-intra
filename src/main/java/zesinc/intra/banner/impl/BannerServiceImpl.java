/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.banner.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.component.file.domain.FileVO;
import zesinc.core.lang.Validate;
import zesinc.intra.banner.BannerMapper;
import zesinc.intra.banner.BannerService;
import zesinc.intra.banner.domain.BannerDomnVO;
import zesinc.intra.banner.domain.BannerVO;
import zesinc.intra.banner.support.BannerUtil;
import zesinc.web.support.pager.Pager;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 배너 정보 서비스 구현 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-07-19.    방기배   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opBannerService")
public class BannerServiceImpl extends EgovAbstractServiceImpl implements BannerService {

    @Resource(name = "opBannerDao")
    private BannerMapper opBannerDao;

    @Override
    public BannerVO selectBanner(BannerVO bannerVo) {

        BannerVO dataVo = opBannerDao.selectBanner(bannerVo);
        // 도메인
        dataVo.setDomnList(opBannerDao.selectBannerDomnList(dataVo));

        return dataVo;
    }

    @Override
    public Pager<BannerVO> selectBannerPageList(BannerVO bannerVo) {

        List<BannerVO> dataList = opBannerDao.selectBannerList(bannerVo);
        Integer totalNum = opBannerDao.selectBannerListCount(bannerVo);

        for(BannerVO dataVo : dataList) {
            dataVo.setDomnList(opBannerDao.selectBannerDomnList(dataVo));
        }

        return new Pager<BannerVO>(dataList, bannerVo, totalNum);
    }

    @Override
    public List<BannerVO> selectBannerSortSnList(BannerVO bannerVo) {

        List<BannerVO> dataList = opBannerDao.selectBannerSortSnList(bannerVo);

        return dataList;
    }

    @Override
    public Integer insertBanner(BannerVO bannerVo) {

        // 파일
        if(Validate.isNotEmpty(bannerVo.getFileList())) {
            FileVO fileVo = bannerVo.getFileList().get(0);
            bannerVo.setFilePathNm(fileVo.getFileUrlAddr());
        }

        if(Validate.isNotEmpty(bannerVo.getBgngYmd())) {
            bannerVo.setBgngYmd(bannerVo.getBgngYmd().replace("-", ""));
        }
        if(Validate.isNotEmpty(bannerVo.getEndYmd())) {
            bannerVo.setEndYmd(bannerVo.getEndYmd().replace("-", ""));
        }

        Integer insertCnt = opBannerDao.insertBanner(bannerVo);

        // 도메인
        if(Validate.isNotEmpty(bannerVo.getSiteSns())) {
            BannerDomnVO bannerDomnVo = new BannerDomnVO();
            bannerDomnVo.setRegSn(bannerVo.getRegSn());

            for(Integer siteSn : bannerVo.getSiteSns()) {
                bannerDomnVo.setSiteSn(siteSn);
                opBannerDao.insertBannerDomn(bannerDomnVo);
            }
        }

        return insertCnt;
    }

    @Override
    public Integer updateBanner(BannerVO bannerVo) {

        // 도메인
        if(Validate.isNotEmpty(bannerVo.getSiteSns())) {
            // 기존 목록 삭제
            opBannerDao.deleteBannerDomn(bannerVo);

            BannerDomnVO bannerDomnVo = new BannerDomnVO();
            bannerDomnVo.setRegSn(bannerVo.getInteger("q_regSn"));
            for(Integer siteSn : bannerVo.getSiteSns()) {
                bannerDomnVo.setSiteSn(siteSn);
                opBannerDao.insertBannerDomn(bannerDomnVo);
            }
        }
        // 파일
        if(Validate.isNotEmpty(bannerVo.getFileList())) {
            BannerVO dataVo = selectBanner(bannerVo);
            // 파일 삭제
            if(Validate.isNotEmpty(dataVo.getFilePathNm())) {
                BannerUtil.deleteFile(dataVo.getFilePathNm());
            }
            // 신규 파일 등록
            FileVO fileVo = bannerVo.getFileList().get(0);
            bannerVo.setFilePathNm(fileVo.getFileUrlAddr());
        }

        if(Validate.isNotEmpty(bannerVo.getBgngYmd())) {
            bannerVo.setBgngYmd(bannerVo.getBgngYmd().replace("-", ""));
        }
        if(Validate.isNotEmpty(bannerVo.getEndYmd())) {
            bannerVo.setEndYmd(bannerVo.getEndYmd().replace("-", ""));
        }

        return opBannerDao.updateBanner(bannerVo);
    }

    @Override
    public Integer deleteBanner(BannerVO bannerVo) {

        // 파일
        BannerVO dataVo = selectBanner(bannerVo);

        // 도메인
        opBannerDao.deleteBannerDomn(bannerVo);

        Integer deleteCnt = opBannerDao.deleteBanner(bannerVo);

        if(Validate.isNotEmpty(dataVo.getFilePathNm())) {
            BannerUtil.deleteFile(dataVo.getFilePathNm());
        }

        return deleteCnt;
    }

    @Override
    public Integer updateBannerSortSn(BannerVO bannerVo) {
        Integer updateCnt = 0;

        int sortSn = 1;
        Integer[] regSns = bannerVo.getRegSns();

        if(Validate.isNotEmpty(regSns)) {
            for(Integer regSn : regSns) {
                bannerVo.setRegSn(regSn);
                bannerVo.setSortSn(sortSn++);

                updateCnt += opBannerDao.updateBannerSortSn(bannerVo);
            }
        }

        return updateCnt;
    }
}

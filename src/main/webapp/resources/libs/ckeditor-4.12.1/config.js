/**
 * @license Copyright (c) 2003-2019, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
    config.allowedContent = true;
    /*
     * 에디터에 첨부파일 제공시 아래 내용을 참조(전역 및 개별기능 설정)
     */
    /* 전역설정(모든 에디터) */
    config.filebrowserImageUploadUrl = "/component/file/ND_fileUpload.do";
    config.filebrowserUploadMethod = "form";

     /*개별기능 설정 (아래 셈플과 같이 해당 에디터 초기화에 filebrowserImageUploadUrl 옵션 추가 */
     /*$("#대상ID").ckeditor({
        toolbar : toolbar_config.default_toolbar,
        filebrowserImageUploadUrl: "/component/file/ND_fileUpload.do"
     });*/
};


	/**
	 * 목록
	 */
	var opList = function() {
		location.href = "BD_crud.list.do?" + opSearchQueryString("dataForm");
	};

	/**
	 * 상세
	 */
	var opView = function(seq) {
		$("#q_regSn").val(seq);
		location.href = "BD_crud.view.do?" + opSearchQueryString("dataForm");
	};

	/**
	 * 등록 폼
	 */
	var opInsertForm = function() {
		$("#q_regSn").val("");
		location.href = "BD_crud.insert.form.do?" + opSearchQueryString("dataForm");	
	};

	/**
	 * 수정 폼
	 */
	var opUpdateForm = function() {
		location.href = "BD_crud.update.form.do?" + opSearchQueryString("dataForm");	
	};

	/**
	 * 삭제
	 */
	var opDelete = function() {
		$("#dataForm").attr("action","ND_crud.delete.do");
		$("#dataForm").submit();
	};

	/**
	 * 목록 삭제
	 */
	var opDeleteList = function() {
		$("#searchForm").attr("action","ND_crud.list.delete.do");
		$("#searchForm").submit();
	};

	/**
	 * 답변 등록 폼
	 */
	var opReplyInsertForm = function() {
		location.href = "BD_crud.reply.insert.form.do?" + opSearchQueryString("dataForm");	
	};

	/**
	 * 답변 수정 폼
	 */
	var opReplyUpdateForm = function(replySeq) {
		$("#q_replySeq").val(replySeq);
		location.href = "BD_crud.reply.update.form.do?" + opSearchQueryString("dataForm");	
	};

	/**
	 * 답변 삭제
	 */
	var opReplyDelete = function(replySeq) {
		$("#q_replySeq").val(replySeq);
		$("#dataForm").attr("action","ND_crud.reply.delete.do");
		$("#dataForm").submit();
	};


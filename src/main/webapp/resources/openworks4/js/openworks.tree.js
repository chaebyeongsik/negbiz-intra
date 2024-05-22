/*
 * 활용참조 사이트 : http://wwwendt.de/tech/fancytree/demo/
 *                          https://github.com/mar10/fancytree/
 * TREE_DATA 셈플 코드. JAVA라면 List 와 Map 구조여야 한다.
 *  var treeData = [
 *       {title: "Folder", folder: true, key: "id3",
 *           children: [
 *               {title: "Sub-item 3.1",
 *                   children: [
 *                       {title: "Sub-item 3.1.1", key: "id3.1.1" },
 *                       {title: "Sub-item 3.1.2", key: "id3.1.2" }
 *                   ]
 *               }
 *           ]
 *       },
 *   ];
 */

(function($) {
    $.fn.optree = function(options) {
        var defaults = {
            // table 레이아웃 사용여부
            USE_TABLE : false,
            // Drag and drop 사용여부
            USE_DND : true,
            // 제목 앞에 checkbox 사용여부
            USE_CHKBOX : false,
            // 폴더, 파일등의 아이콘 사용여부
            USE_ICON : true,
            // ROOT_NODE_NM, ROOT_NODE_VAL의 값이 설정된 root node 사용여부
            USE_ROOT_NODE : true,
            // ROOT node 클릭시 view 페이지 열기 여부
            USE_ROOT_VIEW : false,
            // 폴더 node인 경우 마우스 클릭시 상세 URL(VIEW_URL) 호출여부 
            FOLDER_OPEN : true,
            // 상세 화면 호출시 결과를 표시할 div 태그 id
            CONT_PAN_ID : "treeCont",
            // node를 선택하는 방식 선택 1:하나 선택, 2:복수 선택, 
            // 3:계층형 선택(부모 선택시 자식 모두 선택, 자식 선택시 부모 자동 선택) 
            SELECT_MODE : 1,
            // 추가 파라미터가 필요한 경우에 사용자 정의 추가
            BASE_PARAM_NM : "",
            BASE_PARAM_VAL : "",
            // USE_ROOT_NODE가 true 인 경우 node를 생성할 key, title 값
            ROOT_NODE_NM : "부서목록",
            ROOT_NODE_VAL : OpConfig.defaultCode.highDeptCd,
            // 폴더를 열어 자식node 목록을 가져올 때 사용 될 쿼리조건 key와 value 
            P_NODE_NM : "q_upDeptCdId",
            P_NODE_VAL : OpConfig.defaultCode.highDeptCd,
            // 상세화면 호출시 사용될 해당 node의 키
            NODE_NM : "q_deptCdId",
            // 이동시 현재 일련번호 값을 전달할 파라미터 키명칭
            ORDER_NM : "q_sortOrdr",
            // 목록 json 데이터를 가져오기 위한 URL
            SRC_URL : "/intra/dept/ND_selectDeptTreeList.do",
            // 상세화면 URL
            VIEW_URL : "",
            // DnD 옵션 활성화로 node를 이동할 경우 변경정보를 반영할 URL
            MOVE_URL : "",
            // 한번에 읽어서 처리하고자 할 경우 (본 파일 최상단 'TREE_DATA 셈플 코드' 참조)
            TREE_DATA : ""
        };

        // 트리를 표시할 객체를 설정하지 않은 경우 현재 대상의 id 값을 사용
        if (!options || !options.TREE_PAN_ID) {
            $.extend(true, defaults, {
                TREE_PAN_ID : $(this).attr("id")
            });
        }

        // 사용자 옵션 추가
        var settings = $.extend(true, {}, defaults, options);
        var returnObj = new $.openworkstree();
        returnObj.init(settings);
        returnObj.expandKey(settings.ROOT_NODE_VAL);

        return returnObj;
    };
})(jQuery);

// 초기화
$.openworkstree = function() {
    // 실 트리객체
    this.treeObj;
    this.settings;
};

$.extend($.openworkstree, {

    prototype : {
        /*
         * 현재 트리 객체를 삭제한다.
         * 다시 사용하기 위해서는 새롭게 객체를 생성하여 사용하도록 한다.
         */
        distory : function() {
            $("#" + this.settings.TREE_PAN_ID).fancytree("destroy");
        },
        /*
         * 초기화(외부 호출 금지)
         */
        init : function(_settings) {
            this.settings = _settings;
            this.treeObj = renderTree(this.settings);
        },
        /*
         * tree 를 새로고침 한다.
         */
        reload : function() {
            this.treeObj.reload();
            this.expandKey(this.settings.ROOT_NODE_VAL);
            // 트리 목록을 초기화 하는 경우 컨텐츠 패널도 초기화 한다. 
            // 단 아래와 같이 initView 메소드가 존재해야 한다.
            if(typeof initView == "function") {
                initView(this.settings.CONT_PAN_ID);
            }
        },
        /*
         * 선택된 node의 경로를 반환한다. nodeKey가 지정된 경우 지정된 node를 대상으로 한다. 
         * delimiter 를 지정할 수 있으며 미지정시 기본 delimiter는 ' &gt; ' 이다.
         */
        getNodePath : function(delimiter, nodeKey) {
            var nodePath = "";
            var nodeArr = [];

            var node;
            if (nodeKey) {
                node = this.nodeByKey(nodeKey);
            } else {
                node = this.activeNode();
            }

            if (node) {
                var delim;
                if (delimiter) {
                    delim = " " + delimiter + " ";
                } else {
                    delim = " &gt; ";
                }

                var currNode = node;
                while (currNode && !currNode.isRootNode()) {
                    nodeArr[nodeArr.length] = currNode.title;
                    currNode = currNode.parent;
                }

                nodeArr.reverse();

                for (var i = 0; i < nodeArr.length; i++) {
                    nodePath += (delim + nodeArr[i]);
                }
                nodePath = nodePath.substring(6);
            }

            return nodePath;
        },
        /*
         * 선택된 node의 title을 변경한다. nodeKey가 지정된 경우 지정된 node를 대상으로 한다.
         */
        setTitle : function(title, nodeKey) {
            var node;
            if (nodeKey) {
                node = this.nodeByKey(nodeKey);
            } else {
                node = this.activeNode();
            }
            if (node) {
                node.setTitle(title);
            }
        },
        /*
         * 선택된 node에 useYn 값에 따라서 사용/미사용 글자색을 지정한다.
         * nodeKey가 지정된 경우 지정된 node를 대상으로 한다.
         */
        setUseYn : function(useYn, nodeKey) {
            var node;
            if (nodeKey) {
                node = this.nodeByKey(nodeKey);
            } else {
                node = this.activeNode();
            }
            if (node) {
                // if(useYn) {
                // node[extraClasses] = "text-danger";
                // } else {
                // node[extraClasses] = "";
                // }
                node.renderStatus();
                node.renderTitle();
                node.setFocus(true);
            }
        },
        /*
         * 선택된 node가 폴더(자식이 있는 경우)인지 여부를 true/false로 반환한다.
         * nodeKey가 지정된 경우 지정된 node를 대상으로 한다.
         */
        isFolder : function(nodeKey) {
            var node;
            if (nodeKey) {
                node = this.nodeByKey(nodeKey);
            } else {
                node = this.activeNode();
            }
            if (node) {
                return (node.hasChildren() || node.isFolder());
            }
            return false;
        },
        /*
         * 선택된 node에 자식 node를 추가한다. 추가할 node는 아래와 같아야 한다.
         * {"key" : "키", "title": "제목", "folder" : true, "lazy": true, "customKey1": "customVal1", "customKey2": "customVal2"}
         * 
         * 참고 : lazy 는 ajax data와 같이 현재는 데이터가 없는 경우에 설정하며 openworks에서는 기본형이다.
         */
        addChildren : function(childNode, nodeKey) {
            var node;
            if (nodeKey) {
                node = this.nodeByKey(nodeKey);
            } else if(this.activeNode()){
                node = this.activeNode();
            } else {
                node = this.treeObj.getRootNode();
            }
            if (node) {
                // 자식목록이 있는 폴더인경우와 없는 경우 분리
                if(this.isFolder(node.key)) {
                    // 펼침상태인 경우 추가동작만 실행 닫혀있는 경우 추가 후 펼침상태로 변경
                    if(node.isExpanded()) {
                        node.addChildren(childNode);
                    } else {
                        node.folder = true;
                        node.setFocus(true);
                        node.setActive(true);
                        node.setExpanded(true);
                        /*
                         *  폴더이면서 자식이 있는 경우는 이미 lazy 옵션이 false 상태로
                         *  DB에서 읽어 초기화가 끝난 상태이며 이후 폴더를 열어도 
                         *  DB에서 목록을 갱싱하지 않으므로, UI상에서 추가한다.
                         */
                        if(node.isFolder() && node.hasChildren()) {
                            node.addChildren(childNode);
                        }
                    }
                } else {
                    // 최하위 노드인경우
                    node.addChildren(childNode);
                    node.folder = true;
                    node.setFocus(true);
                    node.setActive(true);
                    node.setExpanded(true);
                }
            }
        },
        /*
         * 선택된 node를 삭제한다. nodeKey가 지정된 경우 지정된 node를 대상으로 한다.
         */
        removeNode : function(nodeKey) {
            var node;
            if (nodeKey) {
                node = this.nodeByKey(nodeKey);
            } else {
                node = this.activeNode();
            }
            if (node) {
                var parent = node.parent;
                node.remove();
                // node 삭제후 자식이 없으면 아이콘을 일반형으로 변경
                if(!parent.hasChildren()) {
                    parent.folder = false;
                    parent.setFocus(true);
                }
            }
        },
        /*
         * 현재 활성화(선택)된 node를 반환
         */
        activeNode : function() {
            return this.treeObj.getActiveNode();
        },
        /*
         * 키에 해당하는 node를 반환한다.
         */
        nodeByKey : function(nodeKey) {
            return this.treeObj.getNodeByKey(nodeKey);
        },
        /*
         * 키에 해당하는 node 자식 모두를 펼친다.
         */
        expandKey : function(nodeKey) {
            node = this.treeObj.getNodeByKey(nodeKey);
            if (node) {
                this.expandChildNode(node);
            }
        },
        /*
         * 모든 node를 전부 펼친다
         */
        expandAll : function() {
            this.treeObj.visit(function(node) {
                node.setExpanded(true);
            });
        },
        /*
         * 모든 node를 전부 접는다.
         */
        collapseAll : function() {
            this.treeObj.visit(function(node) {
                node.setExpanded(false);
            });
        },
        /*
         * 현재 선택된 node의 같은 레벨의 node를 전부 펼친다.
         * nodeKey가 지정된 경우 지정된 node를 대상으로 한다.
         */
        expandSibling : function(nodeKey) {
            var node;
            if (nodeKey) {
                node = this.nodeByKey(nodeKey);
            } else {
                node = this.activeNode();
            }
            if(node) {
                // 현재 노드 열기
                node.setExpanded(true);
                // 이전 노드들 열기
                var prvNdoe = node.getPrevSibling();
                while(prvNdoe) {
                    prvNdoe.setExpanded(true);
                    prvNdoe = prvNdoe.getPrevSibling();
                }
                // 이후 노드들 열기
                var nextNode = node.getNextSibling();
                while(nextNode) {
                    nextNode.setExpanded(true);
                    nextNode = nextNode.getPrevSibling();
                }
            }
        },
        /*
         * 전달된 node의 자식 node를 전부 펼친다.
         */
        expandChildNode : function(node) {
            node.setExpanded(true);
            var hasChild = node.hasChildren();
            if(hasChild) {
                var childs = node.getChildren();
                for(var i=0 ; i < childs.length ; i++) {
                    childs[i].setExpanded(true);
                }
            }
        },
        /*
         * 전달된 node의 자식 node를 전부 접는다.
         */
        collapseChildNode : function(node) {
            var hasChild = node.hasChildren();
            if(hasChild) {
                var childs = node.getChildren();
                for(var i=0 ; i < childs.length ; i++) {
                    childs[i].setExpanded(false);
                }
            }
            node.setExpanded(false);
        },
        /*
         * 전달된 node의 부모노드를 전부 펼친다.
         */
        expandParentNode : function(node) {
            var parentNode = node;
            while (parentNode && !parentNode.isRootNode()) {
                parentNode.setExpanded(true);
                parentNode = parentNode.parent;
            }
        }
    }
});

var renderTree = function(options) {

    // DND / TABLE 사용여부에 따라서 분기
    var extens = new Array();
    if (options.USE_DND) {
        extens[extens.length] = "dnd";
        extens[extens.length] = "edit";
    } else if (options.USE_TABLE) {
        extens[extens.length] = "table";
    }

    var treeOptions = {
        // Enable WAI-ARIA support.
        aria : false,
        // Make sure, active nodes are visible (expanded).
        activeVisible : true,
        // Automatically activate a node when it is focused (using keys).
        autoActivate : true,
        // Automatically collapse all siblings, when a node is expanded.
        autoCollapse : false,
        // Automatically scroll nodes into visible area.
        autoScroll : false,
        // 1:activate, 2:expand, 3:activate and expand, 4:activate (dblclick expands)
        clickFolderMode : 4,
        // Show checkboxes.
        checkbox : options.USE_CHKBOX,
        // 0:quiet, 1:normal, 2:debug
        debugLevel : options.DEBUG_LEVEL,
        // Disable control
        disabled : false,
        // Set focus when node is checked by a mouse click
        focusOnSelect : false,
        // Generate id attributes like <span id='fancytree-id-KEY'>
        generateIds : false,
        // Used to generate node id´s like <span id='fancytree-id-<key>'>.
        idPrefix : "op_",
        // Display node icons.
        icons : options.USE_ICON,
        // Support keyboard navigation.
        keyboard : true,
        // Used by node.getKeyPath() and tree.loadKeyPath().
        keyPathSeparator : "/",
        // 1: root node is not collapsible
        minExpandLevel : 1,
        // Navigate to next node by typing the first letters.
        quicksearch : false,
        // 1:single, 2:multi, 3:multi-hier
        selectMode : options.SELECT_MODE,
        // Whole tree behaves as one single control
        tabbable : true,
        // Node titles can receive keyboard focus
        titlesTabbable : false,
        // plugin
        extensions : extens,
        source : options.TREE_DATA ? options.TREE_DATA : null,
        select : options.select ? options.select : null,
        table : options.table ? options.table : null,
        renderColumns : options.renderColumns,
        dnd : {
            // autoExpandMS: 400,
            // focusOnClick : true,
            // Prevent dropping nodes 'before self', etc.
            // preventVoidMoves : true,
            // Prevent dropping nodes on own descendants
            preventRecursiveMoves : true,
            dragStart : function(node, data) {
                return true;
            },
            dragEnter : function(node, data) {
                var successMove = true;
                if (typeof treeMoveEnter == "function") {
                    successMove = treeMoveEnter(node, data, options);
                }
                return successMove;
            },
            dragDrop : function(node, data) {
                data.otherNode.moveTo(node, data.hitMode);
                if (data.hitMode == "over") {
                    data.node.folder = true;
                }
                data.otherNode.setFocus(true);
                if (typeof treeMove == "function") {
                    treeMove(node, data, options);
                }
            }
        },
        click : options.click ? options.click :  function(event, data) {
            // 임의로 생성된 더미 루트의 경우 선택불가
            if(!options.USE_ROOT_VIEW && (options.ROOT_NODE_VAL == data.node.key)) {
                opWarningMsg("선택할 수 없는 항목입니다.");
                return false;
            }
            // 폴더 열기 옵션이 false 인 경우 클릭안됨 
            if (!options.FOLDER_OPEN && data.node.isFolder()) {
                return false;
            }
            // 클릭 대상이 폴더 또는 체크박스인 경우 treeView를 호출하지 않음
            var targetType = data.targetType;
            if(targetType != "expander" && targetType != "checkbox") {
                if (typeof treeView == "function") {
                    treeView(event, data, options);
                }
            }
        },
        lazyLoad : options.lazyLoad ? options.lazyLoad :  function(event, data) {
            param = {};
            param[options.P_NODE_NM] = data.node.key;
            if(options.BASE_PARAM_NM != "") {
                param[options.BASE_PARAM_NM] = options.BASE_PARAM_VAL;
            }

            data.result = {
                url : options.SRC_URL,
                data : param,
                cache : false
            };
        }
    };

    /*
     * TREE_DATA가 있다면 사용하고 없으면 AJAX로 처리한다.
     * 
     * ROOT_NODE 사용여부에 따라서 분기 미사용시 P_NODE_NM / P_NODE_VAL 값으로
     * 트리 데이터를 ajax로 요청하여 P_NODE_VAL 를 기준으로 한다.
     */
    if(!options.TREE_DATA) {
        if (options.USE_ROOT_NODE) {
            var rootNode = {
                source : [{
                    "key" : options.ROOT_NODE_VAL,
                    "title" : options.ROOT_NODE_NM,
                    "lazy" : true,
                    "folder" : true
                }]
            };
            $.extend(true, treeOptions, rootNode);
        } else {
            var param = {};
            param[options.P_NODE_NM] = options.P_NODE_VAL;
            if(options.BASE_PARAM_NM != "") {
                param[options.BASE_PARAM_NM] = options.BASE_PARAM_VAL;
            }
            var rootNode = {
                source : {
                    url : options.SRC_URL,
                    data : param,
                    cache : false
                }
            };
            $.extend(true, treeOptions, rootNode);
        }
    }

    $("#" + options.TREE_PAN_ID).fancytree(treeOptions);

    var tree = $("#" + options.TREE_PAN_ID).fancytree("getTree");

    return tree;
};

/*
 * 새로 고침
 */
var reload = function() {
    if(Optree) {
        Optree.reload();
    }
};

/*
 * 모든 트리 펼치기
 */
var expandAll = function() {
    if(Optree) {
        Optree.expandAll();
    }
};

/*
 * 모든 트리 접기
 */
var collapseAll = function() {
    if(Optree) {
        Optree.collapseAll();
    }
};

/*
 * 마우스 클릭시 기본 이벤트 처리 함수
 */
var treeView = function(event, data, options) {
    if (!options.VIEW_URL) {
        return;
    }

    var params = {};
    if (options.BASE_PARAM_NM) {
        params[options.BASE_PARAM_NM] = options.BASE_PARAM_VAL;
    }
    params[options.P_NODE_NM] = data.node.parent.key;
    params[options.NODE_NM] = data.node.key;
    params["refresh"] = new Date().toTimeString();

    $("#" + options.CONT_PAN_ID).load(options.VIEW_URL, params, function(responseTxt, statusTxt, response) {
        if(statusTxt == "success") {
            if (typeof afterTreeView == "function") {
                afterTreeView(data.node.key, data.node.parent.key, response);
             }
        } else {
            opSysErrorMsg(responseTxt);
        }
    });
};

/*
 * 노드 이동시 검증 조건을 사용하기 위한 메소드로 return false; 가 되면
 * 화면상에 대상 노드에 끌어가면 X 마크가 나오고 이동 불가 상태가 된다.
 * 
 * 아래 예제코드를 보면 대상 노드의 기 값이 dummy 이면 X 마크와 함께 이동불가 대상이 된다.
 * 예제코드 : if(data.node.key == "dummy") return false;  
 */
var treeMoveEnter = function(node, data, options) {

    return true;
};

/*
 * 노드 이동시 기본 이벤트 처리 함수
 * 
 * data.node 또는 node :  이동 될 대상 node 
 * data.otherNode : 현재 이동되고 있는 node
 * data.hitMode : 대상의 이전/이후 구분 
 *      before : 아래에서 위로 이동시에는 이동위치의 아래 node 
 *      after : 위에서 아래로 이동시에는 이동위치의 위 node 
 *      over : 대상노드위 하위로 이동시 부모 node 
 */
var treeMove = function(node, data, options) {
    if (!options.MOVE_URL) {
        return false;
    }

    var params = {};
    if (options.BASE_PARAM_NM) {
        params[options.BASE_PARAM_NM] = options.BASE_PARAM_VAL;
    }

    // 하위로 이동되는 경우는 대상이 부모이므로 대상의 key를 사용
    var pNodeKey = node.parent.key;
    if (data.hitMode == "over") {
        pNodeKey = node.key;
    }

    params[options.P_NODE_NM] = pNodeKey;
    params[options.NODE_NM] = data.otherNode.key;
    params[options.ORDER_NM] = data.otherNode.getIndex();
    params["refresh"] = new Date().toTimeString();

    $.ajax({
        type: "get",
        dataType : "json",
        url: options.MOVE_URL,
        data: params,
        success: function(response, statusTxt, xhr){
            opJsonMsg(response);
            if (typeof afterTreeMove == "function") {
               return afterTreeMove(data.otherNode.key, pNodeKey, data.otherNode.getIndex(), response);
            }
        },
        error: function(response,status,error) {
            opSysErrorMsg(response.responseText);
            return;
        }
    });
};

/*
 * 트리 이동 후 추가 사용자 정의 메소드
 */
var afterTreeView = function(nodeKey, pNodeKey, orderNo, result) {
    //alert("afterTreeMove : 트리 이동 후 펑션 [부모노드코드 = " + pNodeKey + ", 노드코드 = " + nodeKey + ", 정렬순번 = "+orderNo+"]");

    return true;
};
/*
 * 트리 이동 후 추가 사용자 정의 메소드
 */
var afterTreeMove = function(nodeKey, pNodeKey, orderNo, result) {
    //alert("afterTreeMove : 트리 이동 후 펑션 [부모노드코드 = " + pNodeKey + ", 노드코드 = " + nodeKey + ", 정렬순번 = "+orderNo+"]");

    return true;
};

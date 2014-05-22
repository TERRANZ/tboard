$("#board").ready(loadBoard());
function loadBoard(){
    var url = window.location.href;
    var board = url.substring(url.lastIndexOf("/")+1,url.length);
    $("#board_name").html(board);
    $.ajax({
        		url : '/tb/post/do.get.board.json',
        		async : true,
        		type : 'get',
        		data : { board : board ,
        		all : false,
        		page : 0,
        		perpage : 5},
        		success : function(data) {
            			var htmlRet = "";
            			$.each(data, function(i, post) {
                          				    htmlRet += post.id;
                          				    htmlRet += '<br>';
                          					htmlRet += post.title;
                          					htmlRet += '<br>';
                           					htmlRet += post.comment;
                           					htmlRet += '<br>';
                                            htmlRet += '<a href="#" onclick="openThread(';
                                            htmlRet += "'"+board + "',";
                                            htmlRet += post.id + ');">';
                           					htmlRet += 'Перейти в тред</a>';
                         					htmlRet += '<hr>';
            			});
           				$("#board").html(htmlRet);
       				}
        	});
}

function openThread(board, thread) {
    window.location.assign("/tb/ui/board/"+board+"/t/"+thread);
}

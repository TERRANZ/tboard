$("#thread").ready(loadThread());
function loadThread(){
    var url = window.location.href;
    var thread = url.substring(url.lastIndexOf("/")+1, url.length);
    var board = url.substring(0,url.lastIndexOf("/")-2);
    board = url.substring(url.lastIndexOf("/")-3,board.length);
    $("#thread_name").html(thread);
    $.ajax({
        		url : '/tb/post/do.get.thread.json',
        		async : true,
        		type : 'get',
        		data : { board : board ,
        	    thread : thread},
        		success : function(data) {
            			var htmlRet = "";
            			$.each(data, function(i, post) {
                          				    htmlRet += post.id;
                          				    htmlRet += '<br>';
                                            if (post.image != null)
                                                htmlRet += '<img src="/tb/resources/picz/'+thread+'/'+post.image+'">';
                          				    htmlRet += '<br>';
                          					htmlRet += post.title;
                          					htmlRet += '<br>';
                           					htmlRet += post.comment;
                         					htmlRet += '<hr>';
            			});
           				$("#thread").html(htmlRet);
       				}
        	});
}

function add(){
    var url = window.location.href;
    var thread = url.substring(url.lastIndexOf("/")+1, url.length);
    var board = url.substring(0,url.lastIndexOf("/")-2);
    board = url.substring(url.lastIndexOf("/")-3,board.length);
    $("#thread_name").html(thread);
    var file = document.getElementById("file");
    /* Create a FormData instance */
    var formData = new FormData();
    /* Add the file */
    formData.append("file", file.files[0]);
    formData.append("thread", thread);
    formData.append("board", board);
    formData.append("parent", thread);
    formData.append("title", $("#title").val());
    formData.append("comment", $("#comment").val());
    $.ajax({
        url : '/tb/post/do.add',
        type : 'post',
        async : true,
        data : formData,
        dataType: 'json',
        processData: false,
        contentType: false,
        success : function(data) {
            $("#title").val("");
            $("#comment").val("");
            $("#file").val("");
            loadThread();
        }
    });

}
/**
 * 一级回复功能
 */
function reply() {
    var context = $("#context").val();
    var parentId = $("#parentId").val();
    var type = "0";
    var commentator = $("#commentator").val();
    if (!context){
        alert("回复不能为空哦~");
        return;
    }

    $.ajax({
        type: "post",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": parentId,
            "context": context,
            "type": type,
            "commentator": commentator
        }),
        dataType: "json",
        success: function (response) {
            if (response.code == 200) {
                $("#context").val("");
            } else {
                alert(response.msg)
            }
        }
    });
}

/**
 * 二级回复
 */
function reply2(e) {
    var attribute = e.getAttribute("data");


    var context = $("#context"+attribute).val();
    var parentId = $("#parentId"+attribute).val();
    var type = $("#type"+attribute).val();
    var commentator = $("#commentator"+attribute).val();
    if (!context){
        alert("评论不能为空哦~");
        return;
    }

    $.ajax({
        type: "post",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": parentId,
            "context": context,
            "type": type,
            "commentator": commentator
        }),
        dataType: "json",
        success: function (response) {
            if (response.code == 200) {
                $("#context"+attribute).val("");
            } else {
                alert(response.msg);
            }
        }
    });

}
/**
 * 二级评论开关功能
 */
function comment_reply(e){
    var attribute = e.getAttribute("data");
    var lock = $("#comment-"+attribute);
    if (lock.hasClass("in")){
        lock.removeClass("in");
    }else {
        lock.addClass("in");
    }
}
function reply() {
    var context = $("#context").val();
    var parentId = $("#parentId").val();
    var type = "1";
    var commentator = $("#commentator").val();
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

                $("#reply-square").hide();
            } else {
                alert(response.msg)
            }
        }
    });
}

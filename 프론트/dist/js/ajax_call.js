$(document).ready(function(){
    $.ajax({
        type:"GET",
        datatype:'json',
        url:"http://duckbo.site:9530/members",
        contentType : 'application/json;charset=UTF-8',
        success : function(data) {

            //comment 위치
            var comment = $('#comment');
            var divLeft = comment.offset().left;
            var divTop = comment.offset().top;

            var bodyWidth = comment.width()-200//document.body.clientWidth;
            var bodyHeight =document.body.clientHeight  * 0.4;
            for(var i in data) {
                var randPosX = Math.floor(divLeft +  (Math.random()*bodyWidth));
                var randPosY = Math.floor(divTop + (Math.random()*bodyHeight));
                var randomDiv = document.createElement("div");
                var com = JSON.stringify(data[i].comment);
                randomDiv.style.left = randPosX;
                randomDiv.style.top =randPosY;
                randomDiv.innerText = com;
                randomDiv.setAttribute("class", "comment-faded");
                randomDiv.style.cssText = "position:fixed;top:" + randPosY + "px;left: " + randPosX + "px;width:200px;height:200px;";
                $("#comment").append(randomDiv);
            }
        }
    });
});
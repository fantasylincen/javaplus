/**
*
*   @Author 孟祥平
*
*   @Description 
*       
*   @See http://www.cnblogs.com/springfield/
*   @See mengxiangpingx@gmail.com
*/

var springweb_basePath;
var springweb_folder = "/spring/"


//check which browser the client using.
var springweb_typeIsIE = false;
var springweb_typeIsGecko = false;
var springweb_typeIsWebkit = false;

var springweb_typeIsIE6 = false;
var springweb_typeIsIE7 = false;
var springweb_typeIsIE8 = false;
var springweb_typeIsFireFox = false;
var springweb_typeIsChrome = false;
var springweb_typeIsSafari = false;

var agent = window.navigator.userAgent;

if (agent.indexOf("MSIE 6") != -1) {
    springweb_typeIsIE6 = true;
    springweb_typeIsIE = true;
}
else if (agent.indexOf("MSIE 7") != -1) {
    springweb_typeIsIE7 = true;
    springweb_typeIsIE = true;
}
else if (agent.indexOf("MSIE 8") != -1) {
    springweb_typeIsIE8 = true;
    springweb_typeIsIE = true;
}
else if (agent.indexOf("Firefox") != -1) {
    springweb_typeIsFireFox = true;
    springweb_typeIsGecko = true;
} else if (agent.indexOf("Chrome") != -1) {
    springweb_typeIsChrome = true;
    springweb_typeIsWebkit = true;
}
else if (agent.indexOf("Safari") != -1) {
    springweb_typeIsSafari = true;
    springweb_typeIsWebkit = true;
}

// finish check browser


/*
*   
*   @Description 用于生成
*
*/
 
function setBasePath(folder) {
    var url = window.location.href;
    if (folder != undefined) springweb_folder = "/" + folder + "/";
    springweb_basePath = url.substring(0,url.lastIndexOf("/")) + springweb_folder;
}


function richAlert(msg,title,moveable) {

    try {
        
        document.body.style.overflowY = "hidden";
        var divBackground = document.createElement("div");
        divBackground.style.position = "absolute";
        divBackground.style.left = "0px";
        divBackground.style.top = "0px";
        divBackground.style.width = "1px";
        divBackground.style.height = "1px";
        if (springweb_typeIsChrome || springweb_typeIsFireFox) {
            divBackground.style.backgroundColor = "rgba(0,0,0,0.7)";
        } else {
            divBackground.style.backgroundColor = "#000000";    
            divBackground.style.filter = "alpha(opacity=70)";
        }
        divBackground.style.zIndex = "99";
        document.body.appendChild(divBackground);
        
        var divDialog = document.createElement("div");


        //calculate the width and height of the dialog 
        //depends on the length of the message.
        var dialogWidth = 420;
        var dialogHeight = 300;
        var fontSize = 18;
        var lineWidth = document.body.clientWidth * 0.8;
        if ((fontSize * msg.length) < lineWidth) {
            dialogWidth = parseInt(fontSize * msg.length) + 100;
        } else {
            dialogWidth = parseInt(lineWidth);
            dialogHeight += parseInt(((fontSize * msg.length) / lineWidth) * fontSize);
            
        }
        
        //build the ui components. 
        divDialog.style.width = dialogWidth + "px";
        divDialog.style.height = dialogHeight + "px";        
        divDialog.style.position = "absolute";
        divDialog.style.border = "1px solid #C0D7FA";
        divDialog.style.borderRight = "2px outset #DEDEDE";
        divDialog.style.borderLeft = "2px outset #DEDEDE";
        divDialog.style.left = ((document.body.clientWidth / 2) - (dialogWidth / 2)) + "px";
        divDialog.style.top = ((document.body.clientHeight / 2) - (dialogHeight / 2)) + "px";
        divDialog.style.zIndex = "100";
        

        var divHead = document.createElement("div");
        if (title != undefined) {
            divHead.innerHTML = title;
            //divHead.appendChild(document.createTextNode(title));
        } else {
            divHead.appendChild(document.createTextNode("消息"));
        }
        divHead.style.width = "100%";
        divHead.style.height = "40px";
        divHead.style.lineHeight = "25px";
        divHead.style.fontSize = "14px";        
        divHead.style.fontWeight = "bold";
        divHead.style.borderBottom = "1px outset #8989FF";
        divHead.style.color = "white";
        divHead.style.textIndent = "10px";
        divHead.style.backgroundColor = "blue";
        divHead.style.backgroundImage = "url(images/hengtiao.png)";
        divHead.style.cursor = "move";
        divHead.onmousedown = function() {

            divDialog.dragging = true;
            
        };
        divHead.onmouseup = function() {

            divDialog.dragging = false;

        };

        document.body.onmousemove = function(e) {

            if (!divDialog.dragging) return;
            e = e || window.event;
            var mouseX, mouseY;
            var mouseOffsetX, mouseOffsetY;
            if (e.pageX || e.pageY) {
                mouseX = e.pageX;
                mouseY = e.pageY;

            } else {
                mouseX =
                    e.clientX + document.body.scrollLeft -
                    document.body.clientLeft;
                mouseY =
                    e.clientY + document.body.scrollTop -
                    document.body.clientTop;

            }
            
            divDialog.style.left = (mouseX - dialogWidth * 0.4) + "px";
            divDialog.style.top = (mouseY - 100) + "px";            
        };
                
        
        divDialog.appendChild(divHead);

        var divContent = document.createElement("div");
        divContent.style.textAlign = "center";
        divContent.style.padding = "30px";
        divContent.style.fontSize = "40px";

        if (springweb_typeIsIE) {
            divContent.style.height = parseInt(dialogHeight - 25) + "px";
        } else {
            divContent.style.height = parseInt(dialogHeight - 55) + "px";
        }

        divContent.style.backgroundColor = "#ffffff";
        if (springweb_typeIsIE6 || springweb_typeIsIE7 || springweb_typeIsIE8) {
            divContent.style.filter =
            "progid:DXImageTransform.Microsoft.Gradient(gradientType=1,startColorStr=#FFFFFF,endColorStr=#C2E2F8)";
        } else if (springweb_typeIsFireFox) {
            divContent.style.backgroundImage =
            "-moz-linear-gradient(left,rgba(255,255,255,1),rgba(194,226,248,1))";
        } else if (springweb_typeIsWebkit) {
            divContent.style.backgroundImage =
            "-webkit-gardient(linear,0% 0%,100% 100%,from(#FFFFFF),to(#000000))";
        }
        
        
        

        divContent.innerHTML = msg + "<br /><br />";
        

        divDialog.appendChild(divContent);
    
        var closeButton = document.createElement("img");
        closeButton.style.cursor = "hand";
        closeButton.setAttribute("src", "images/queding.png");
        closeButton.setAttribute("width", "120");
        closeButton.setAttribute("height", "60");
        closeButton.setAttribute("alt", "确定");
        
        //the click event when the dialog is closing.
        closeButton.onclick = function() {

            document.body.removeChild(divBackground);
            document.body.removeChild(divDialog);
            document.body.style.overflowY = "";

        };
        divContent.appendChild(closeButton);
        divDialog.focus();
        document.body.appendChild(divDialog);        
    } catch (ex) {

        alert(msg);
    }
    
}



setBasePath();
<%--
  Created by IntelliJ IDEA.
  User: 蒯支睿
  Date: 2019/10/2
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>

<%@include file="../common/IncludeTop.jsp"%>

<div id="Welcome">
    <div id="WelcomeContent">
        Welcome to MyPetStore!
    </div>
</div>

<div id="Main">
    <div id="Sidebar">
        <div id="SidebarContent">
            <a href="ViewCategory?categoryId=FISH"><img src="../../../images/fish_icon.gif" /></a>
            <br/> Saltwater, Freshwater <br/>
            <a href="ViewCategory?categoryId=DOGS"><img src="../../../images/dogs_icon.gif" /></a>
            <br /> Various Breeds <br />
            <a href="ViewCategory?categoryId=CATS"><img src="../../../images/cats_icon.gif" /></a>
            <br /> Various Breeds, Exotic Varieties <br />
            <a href="ViewCategory?categoryId=REPTILES"><img src="../../../images/reptiles_icon.gif" /></a>
            <br /> Lizards, Turtles, Snakes <br />
            <a href="ViewCategory?categoryId=BIRDS"><img src="../../../images/birds_icon.gif" /></a>
            <br /> Exotic Varieties
        </div>
    </div>

    <div id="MainImage">
        <div id="MainImageContent">
            <map name="estoremap">
                <area alt="BIRDS" coords="72,2,280,250" href="ViewCategory?categoryId=BIRDS" shape="rect" />
                <area alt="FISH" coords="2,180,72,250" href="ViewCategory?categoryId=FISH" shape="rect" />
                <area alt="DOGS" coords="60,250,130,320" href="ViewCategory?categoryId=DOGS" shape="rect" />
                <area alt="REPTILES" coords="140,270,210,340" href="ViewCategory?categoryId=REPTILES" shape="rect" />
                <area alt="CATS" coords="225,240,295,310" href="ViewCategory?categoryId=CATS" shape="rect" />
                <area alt="BIRDS" coords="280,180,350,250" href="ViewCategory?categoryId=BIRDS" shape="rect" />
            </map>
            <img height="355" src="../../../images/splash.gif" align="middle" usemap="#estoremap" width="350" />
        </div>
    </div>
    <div id="Separator">&nbsp;</div>
</div>

<%@include file="../common/IncludeBottom.jsp"%>

<script>
    //获得鼠标位置；
    var moveon;
    var x;
    var y;
    document.onmousemove=function(ev){
        var oEvent=ev||event;
        x=oEvent.clientX;
        y=oEvent.clientY;
    }

    var xmlHttpRequest;
    function createXMLHttpRequest()
    {
        if (window.XMLHttpRequest) //非IE浏览器
        {
            xmlHttpRequest = new XMLHttpRequest();
        }
        else if (window.ActiveXObject)//IE6以上浏览器
        {
            xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
        }
        else //IE6及以下浏览器
        {
            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
    }

    var flag = true;
    var imgs = document.getElementsByTagName('area');
    //为每个元素绑定事件
    for(var i=0; i<imgs.length; i++){
        imgs[i].onmouseover = function () {
            if(flag) {
                flag = false;
                printMsg(this);

            }
        }
        imgs[i].onmouseleave = function () {
                clearMsg(this);
                flag = true;

        }
    }

    function printMsg(ob){
        moveon = ob;
        sendRequest("printMsg?categoryId=" + ob.alt);
    }

    function clearMsg(ob) {6
        var div = document.getElementById('Suspension');
        div.parentNode.removeChild(div);
    }

    function sendRequest(url){
        createXMLHttpRequest();
        xmlHttpRequest.open("GET", url, true);
        xmlHttpRequest.onreadystatechange = processResponse;
        xmlHttpRequest.send(null);
    }

    function processResponse(){
        if(xmlHttpRequest.readyState == 4){
            if(xmlHttpRequest.status == 200){
                var JsonResult = eval("(" + xmlHttpRequest.responseText + ")");
                var data = JsonResult.records;
                // console.log(data);
                //生成悬浮窗
                createSuspension(data);
            }
        }
    }

    function createSuspension(data) {
        var div = document.createElement('div');
        div.id = "Suspension";
        for(var i=0; i < data.length; i++){
            var img = document.createElement('img');
            var srcc = getImgSrc(data[i].description);
            img.src = srcc;
            console.log(srcc);
            img.title = "name:" + data[i].name + "\n" +
                      "description:" + getDescription(data[i].description);
            div.appendChild(img);
        }
        div.style.border = "1px solid black";
        div.style.borderRadius = "5px";
        div.style.backgroundColor = "white";
        div.style.position = "absolute";
        div.style.left = x +"px";
        div.style.top = y +"px";
        moveon.appendChild(div);
    }

    function getImgSrc(msg) {
        var index1 = msg.indexOf("=");
        var index2 = msg.indexOf(">");
        return ".." + msg.substring(index1 + 2, index2 - 1);
    }

    function getDescription(msg){
        var index = msg.indexOf(">");
        return msg.substr(index+1);
    }
</script>

<%--
  Created by IntelliJ IDEA.
  User: 蒯支睿
  Date: 2019/10/2
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
</div>

<div id="Footer">

    <div id="PoweredBy">&nbsp<a href="www.csu.edu.cn">www.csu.edu.cn</a>
    </div>

    <div id="Banner">
        IncludeBottom.html
    </div>

</div>

</body>
</html>

<script>
  var xhr;
  function getResponse(){
      if(xhr.readyState == 4){
          if(xhr.status == 200){
              var JsonResult = eval("(" + xhr.responseText + ")");
              if(JsonResult == ""){
                  return;
              }
              var data = JsonResult.records;
              // console.log(data);
              var childs = "";
              //遍历结果集，将结果集中的每一条数据用一个div显示，把所有的div放入到childs中
              for(var i=0; i<data.length;i++){
                  childs += "<div onclick='Write(this)' style='line-height: 18px;font-size: 15px;' onmouseout='recoverColorwhenMouseout(this)' onmouseover='changeColorwhenMouseover(this)'>"+data[i].name+"</div>";
              }
              //把childs 这div集合放入到下拉提示框的父div中，上面我们以获取了
              div.innerHTML=childs;
              div.style.width = "181px"
              div.style.border = "1px solid black";
              div.style.position = "relative";
              div.style.left = "125px";
              div.style.top = "-27px";
              div.style.backgroundColor = "white";
              div.style.display="block";
          }
      }
  }

      //获取文本输入框
      var textElment = document.getElementById("text");
      //获取下提示框
      var div = document.getElementById("tips");

      textElment.onkeyup=function(){
          //获取用户输入的值
          var text = textElment.value;
          //如果文本框中没有值，则下拉框被隐藏，不显示
          if(text==""){
              div.style.display="none";
              console.log(text);
              return;
          }
          xhr = new XMLHttpRequest();
          xhr.open("GET", "findText?text="+text, true);
          xhr.onreadystatechange = getResponse;
          xhr.send(null);
      }
  //鼠标悬停时改变div的颜色
  function changeColorwhenMouseover(div){
          console.log("in");
      div.style.backgroundColor="pink";
  }
  //鼠标移出时回复div颜色
  function recoverColorwhenMouseout(div){
      console.log("out");
      div.style.backgroundColor="";
  }
  //当鼠标带点击div时，将div的值赋给输入文本框
  function Write(div){
      console.log("click");
      //将div中的值赋给文本框
      document.getElementById("text").value=div.innerHTML;
      //让下拉提示框消失
      div.parentNode.style.display="none";
  }
</script>
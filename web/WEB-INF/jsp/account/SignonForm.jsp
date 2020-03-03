<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">

        <form action="Signon" method="post">
            <p>Please enter your username and password.</p>
            <p>
                Username:<input type="text" name="username" /><br /><br />
                Password:<input type="password" name="password" /><br /><br />
                Validation Code:<input type="text" name="code" /><br /><br />
                <a href="#" onclick=changeImageCode()><img id = "imgId" src = "checkcode"></a>
                <a href="#" onclick=changeImageCode()>Another</a>
            </p>

            <input type="submit" name="signon" value="Login"/>
        </form>

        Need a user name and password?
        <a href="newAccount">Register Now!</a>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>

<script>
    function changeImageCode() {
                var image = document.getElementById("imgId");//获取表单元素 通过ID‘
                image.src = "checkcode?"+new Date().getTime();
            }
</script>



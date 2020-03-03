<%@ include file="../common/IncludeTop.jsp"%>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
<%--<script src="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>--%>

<div id="Catalog">

    <form action="newAccount" method="post" name="registerForm" id="registerForm">
        <ul class="tab">
            <li class="cur">User Information</li>
            <li>Account Information</li>
            <li>Profile Information</li>
        </ul>
    <div class="card" style="display:block;">
        <table>
            <tr>
                <td>User ID:</td>
                <td>
                    <input type="text" name="username" id="username" onblur="usernameIsExist();"/>
                    <div id="usernameMsg"></div>
                </td>
            </tr>
            <tr>
                <td>New password:</td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td>Repeat password:</td>
                <td><input type="password" name="repeatedPassword"/></td>
            </tr>
        </table>
    </div>
    <div class="card" style="display:none;">

        <table>
            <tr>
                <td>First name:</td>
                <td><input type="text" name="firstName" placeholder="${sessionScope.account.firstName}"/></td>
            </tr>
            <tr>
                <td>Last name:</td>
                <td><input type="text" name="lastName" placeholder="${sessionScope.account.lastName}" /></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="email" placeholder="${sessionScope.account.email}" /></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><input type="text" name="phone" placeholder="${sessionScope.account.phone}" /></td>
            </tr>
            <tr>
                <td>Address 1:</td>
                <td><input type="text" name="address1" placeholder="${sessionScope.account.address1}" /></td>
            </tr>
            <tr>
                <td>Address 2:</td>
                <td><input type="text" name="address2" size="40" placeholder="${sessionScope.account.address2}" /></td>
            </tr>
            <tr>
                <td>City:</td>
                <td><input type="text" name="city" placeholder="${sessionScope.account.city}" /></td>
            </tr>
            <tr>
                <td>State:</td>
                <td><input type="text" name="state" size="4"placeholder="${sessionScope.account.state}" /></td>
            </tr>
            <tr>
                <td>Zip:</td>
                <td><input type="text" name="zip" size="10" placeholder="${sessionScope.account.zip}" /></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td><input type="text" name="country" size="15" placeholder="${sessionScope.account.country}" /></td>
            </tr>
        </table>
    </div>
    <div class="card" style="display:none;">

        <table>
            <tr>
                <td>Language Preference:</td>
                <td>
                    <select name="languagePreference" id="languagePreference">
                        <option>english</option>
                        <option>japanese</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Favourite Category:</td>
                <td>
                    <select name="favouriteCategoryId" id="favouriteCategoryId">
                        <option>BIRDS</option>
                        <option>CATS</option>
                        <option>DOGS</option>
                        <option>FISH</option>
                        <option>REPTILES</option>
                    </select>
                </td>
            </tr>
            </tr>
            <tr>
                <td>Enable MyList</td>
                <td><input type="checkbox" name="listOption" value="1" /></td>
            </tr>
            <tr>
                <td>Enable MyBanner</td>
                <td><input type="checkbox" name="bannerOption" value="1" /></td>
            </tr>

        </table>
    </div>
    <input type="submit" name="newAccount" value="Save Account Information"/>
	</form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>

<script type="text/javascript">
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

    function usernameIsExist()
    {
        var username = document.registerForm.username.value;
        if(username == ''){
            var div1 = document.getElementById("usernameMsg");
            div1.innerHTML="<font color='red'>User ID can't be null</font>";
        }else {
            sendRequest("usernameValidation?username="+username);
        }

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
                var responseInfo = xmlHttpRequest.responseXML.getElementsByTagName("msg")[0].firstChild.data;
                var div1 = document.getElementById("usernameMsg");

                if (responseInfo == "Exist") {
                    div1.innerHTML = "<font color='red'>User ID can't be used</font>";
                } else {
                    div1.innerHTML = "<font color='green'>User ID can be used</font>";
                }

            }
        }
    }

</script>

<script>
    $(document).ready(function() {
        $(".tab li").click(function() {
            $(".tab li").eq($(this).index()).addClass("cur").siblings().removeClass('cur');
            $(".card").hide().eq($(this).index()).show();
            //另一种方法: $("div").eq($(".tab li").index(this)).addClass("on").siblings().removeClass('on');

        });
    });
</script>
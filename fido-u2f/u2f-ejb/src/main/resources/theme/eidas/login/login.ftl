<#import "template.ftl" as layout>



 
<!DOCTYPE html>
<html>
<head>
<#-- <title>ESMO sp-ms</title> -->
</head>
<#--<!-- <body onload="document.redirect.submit()"> -->
<#--<!-- <form action="${UrlToRedirect}" id="redirect" name="redirect" method="POST"> -->
<#--<!-- <input id="authResponse" type="hidden" value="${msToken}" name="msToken"/> -->
<#--<!-- </form> -->
<body>

<#-- <h1>acmrequest acmUrl: ${acmRequest.acmUrl}</h1> -->
<#-- <h1>acmrequest uri: ${acmRequest.uri}</h1> -->
<#-- <h1>acmrequest msToken: ${acmRequest.msToken}</h1> -->

<#-- <h1>ESMO sp-ms</h1> -->
 <div class="header">
    

  <div>
        Hi from my eIDAS Theme
    </div>
 </div>
<form name="myRedirectForm" action="${eidasUrl}" method="post">
 		<input name="saml" type="hidden" value="${saml}" />
 		<noscript>
            <input type="submit" value="Click here to continue" />
        </noscript>
        <script type="text/javascript">

            (function () {
                document.myRedirectForm.submit();
            })();

        </script>
 	</form>

</body>
</html>



 

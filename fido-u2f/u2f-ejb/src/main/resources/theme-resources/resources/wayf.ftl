<#import "template.ftl" as layout>




<!DOCTYPE html>
<html>
    <head>
<#-- <title>ESMO sp-ms</title> -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
        <meta charset="utf-8"></meta>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"></meta>
        <meta name="viewport" content="width=device-width, initial-scale=1"></meta>
        <meta name="description" content=""></meta>
        <meta name="author" content=""></meta>
        <title>Create a new account</title>
        <meta http-equiv="cache-control" content="max-age=0" />
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="expires" content="0" />
        <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
        <meta http-equiv="pragma" content="no-cache" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"></link>
        <link rel="stylesheet" href="${url.resourcesPath}/css/main.css"></link>
        <link rel="icon"
              type="image/x-icon"
              href="favicon.ico"></link>
        <link rel="icon"
              type="image/x-icon"
              href="favicon.ico"></link>
        <!-- Overide the sidebar css -->
        <style>
            .sideBarClass{
                margin-top: 0;
            }

            .breadCrumbs{
                font-size: 18px;
                color:  #00be9f;
                padding-left: 0px;
            }
            </style>


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
        <header>
            <div class="footerClass headerContainer">
                <!--<div class="container">-->
                <div class=" instructions" style="width:90%">
                    <div class="row">
                        <div class="col s6 m6 l6 " id="header-logo">
                            <img id="companyLogo" class="responsive-img" src= "${url.resourcesPath}/img/logo2.png" style="margin-top: 1.8em;
                                 max-height: 55px;float:left;    padding-left: 40.5%;"/>
                            </div>
                        <div class="col s6 m6 l6 " id="header-logo-aegean">
                            <img id="uAegeanLogo" class="responsive-img" src="${url.resourcesPath}/img/uaegean_logo.png" style="margin-top: 0em;
                                 margin-bottom: 1.2em;
                                 max-height: 85px;float:right; margin-right: 5%;"/>
                            </div>
                        </div>    

                    </div>
                    <!--</div>-->


                </div>
            </header>



        <div class="container">

            <div class="row  mainContent">

                <div class="col s12 m12 l10">

                    <div class="container" style="width: 90%;">

                        <div class="row instructions">
                            <div  id="mobile" class="col s12 ">
                                <p>
                                    <b >Identify with eIDAS eID:</b> 
                                    <span >You will now be directed to the eIDAS Network to securely identify and trustly 
                                        provide us your main identity attributes. Review the identification attributes that will be requested
                                        </span>  
                                    <a href="#modal1" class="modal-trigger" >HERE</a>
                                    </p>
                                <p >
                                    The eIDAS Network will provide us with those attributes from the Identity Provider you will suggest.

                                    The eIDAS Network will request your consent before sending us any information.
                                    After successful authentication you will be redirected to our service.
                                    </p>


                                <div class="row">
                                    <div class="input-field col s12">
                                        <select id="countrySelection" class="icons">
                                            <#list countries as country>
                                                     <option    value="${country.code}"  attr="data-icon= 'img/flags/' + ${country.name} + '_flag.gif'" >${country.name}</option>
                                            </#list>

                                            </select>
                                        <label>Select Your Country of Origin</label>
                                        </div>

                                    <div class="input-field col s12" style="display:none">
                                        <select id="typeOfLogin" class="icons" >
                                            <option value="eIDAS"  >eIDAS</option>
                                            <option value="peps" selected="true">PEPS</option>    
                                            </select>
                                        <label>Select means of identification </label>
                                        </div>
                                    </div>
                                <div class="row">
                                    <div class="col s12 m12 l6">
                                        <a id="cancel" class="waves-effect waves-light btn-large swell-btn cancel-btn" onclick="onCancelClick()" >Cancel</a>
                                        </div>
                                    <div class="col s12 m12 l6">
                                        <a id="next" class="waves-effect waves-light btn-large swell-btn next-btn" onclick="onNextClick()"  >Next</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>




                <div class="col s12 m12 l2">
                    <div class="sideBarClass" style="padding-top: 3em;" >
                        <div class="container">
                            <div class="row">
                                <div class="col s4  l12">
                                    <a target="_blank" href="https://docs.google.com/document/d/1MeRO2ZI97FC_H12WQiOdnMgvfWJ6i6YGTZ6KGUnD4VU/edit?usp=sharing">
                                        <img src="${url.resourcesPath}/img/instructions.png" style="
                                             max-width:  60px;
                                             margin-left: 10%;
                                 "/>
                                        <p style="margin-top:  0; font-size:smaller;font-weight: bold;" >Instructions</p>
                                        </a>
                                    </div>
                                <div class="col s4  l12">
                                    <a target="_blank" href="https://docs.google.com/document/d/17EKb0gAhDPDOuCTgolxR5Ydzxa97VywpHzuM_-c4mdk/edit?usp=sharing" style="margin-left: 0.5rem;">
                                        <i class="medium material-icons">info_outline</i>
                                        <p style="margin-left: 1.2rem; margin-top: 0;margin-bottom: 0;font-weight: bold; font-size:smaller;">About</p>
                                        </a>
                                    </div>

                                <div class="col s4  l12">
                                    <a target="_blank" href="https://docs.google.com/document/d/1UniQ7VVGB_bPFyDLLraBghzS4_Jls-hYcuLJCol13Is/edit">
                                        <img src="${url.resourcesPath}/img/eidas_logo2.jpg" style="max-width: 56px;
                                             margin-left: 10%;"/>
                                        <p style="margin-top: 0;
                                           margin-left: 0.5em; font-size:smaller;font-weight: bold;" >eIDAS eID</p>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>




                    </div>
                <div id="modal1" class="modal modal-fixed-footer" th:fragment="modalProperties">
                    <div class="modal-content" style="padding-bottom: 0">
                        <div class="container">
                            <div class="row">
                                <div class="col s12">
                                    <h4  >Which attributes will be requested</h4>
                                    </div>
                                </div>
                            <div class="row">
                                <div class="col s12" >
                                    By agreeing to proceed the system will request the following identification attributes from the eID_EU network. 
                                    These attributes will not be propagated to third parties and will only be used within the premises of this application.
                                    </div>
                                </div>
                            <div class="row">
                                         <!-- TODO add attributes to display-->
                                </div>
                            <div class="modal-footer">
                                <div class="container">
                                    <div class="row" style="margin-bottom: 0;" >
                                        <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat" >Agree</a>
                                        <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat" onclick="onCancelClick()"> Disagree</a>
                                        </div>
                                    </div>
                                </div>
                            </div>



                        <div class="row" th:replace="footer :: footer"></div>

                        </div>

        <!--Import jQuery before materialize.js-->
                    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
                    <!-- Compiled and minified JavaScript -->
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
                    <script th:inline="javascript">
                                 
                                                    $(document).ready(function () {
                                                        $('select').formSelect();
                                                        $('.modal').modal();
                                                    });
                                                    function onNextClick() {
                                                        console.log("ehy");
            //                                            let countrySelector = $("#countrySelection");
                                                        var country= document.getElementById("countrySelection").value;//countrySelector.val();
                                                        var url = "${bakend}?country=" + country + "&keycloakSession=${state}" ;
                                                            var actionUrl = "${eidas}";
                                                        $.get(url, function (data, status) {
                                                            if (status == "success") {
                                                                let form = $('<form></form>', {
                                                                    method: "post",
                                                                    action: actionUrl
                                                                });
                                                                form.append('<input type="hidden" name="SAMLRequest" value=' + data + '></input>')
                                                                form.append('<input type="hidden" name="country" value=' + country + '></input>');
                                                                $('body').append(form);
                                                                form.submit();
                                                            }
                                                        });
                                                    }
                                     
                        </script>

                    </body>
                    </html>





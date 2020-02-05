<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
        <meta charset="utf-8"></meta>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"></meta>
        <meta name="viewport" content="width=device-width, initial-scale=1"></meta>
        <meta name="description" content=""></meta>
        <meta name="author" content=""></meta>
        <title>${msg("errorTitle")}</title>
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css"></link>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"></link>
        <link rel="stylesheet" href="${url.resourcesPath}/css/main.css"></link>
        <link rel="icon"
              type="image/x-icon"
              href="${url.resourcesPath}/img/favicon.ico"></link>
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
    <body>


        <header  >
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
                                 max-height: 85px;float:right; margin-right: 20%;"/>
                        </div>
                    </div>    
                </div>
            </div>
        </header>

        <div class="container">

            <div class="row  mainContent">

                <div class="col s12 m12 l10">

                    <div class="container" style="width: 90%;">



                        <div class="row instructions">
                            <div class="col s12 flow-text hide-on-large-only">
                                <h5>${msg("errorTitle")}</h5>
                                <p>${message.summary}</p>
                            </div>
                            <div class="col s12 hide-on-med-and-down ">
                                <h5>${msg("errorTitle")}</h5>
                                <p>${message.summary}</p>
                            </div>
                        </div>
                        

                        <div class="row">
                            <div class="col s12 m12 l6 " style="margin-top: 1rem;">
                                Unfortunately, the session cannot be recovered. Please restart the process. 
                            </div>
                        </div>

                    </div>
                </div>




            </div>
            <footer class="page-footer custom-footer"  th:fragment="footer">
                <div class="container footer-container" style="margin-left: 2rem;">
                    <div class="row">
                        <div id="border" style="    border-top: 1px solid #d0d0d0;
                             width: 91%;
                             margin-left: 0.7rem;
                             padding-top: 1em;"></div>
        
                        <div class="col s12 m4 footerCol" id="flags" style="padding-left: 5em;">
                            <a target="_blank" href="https://ec.europa.eu/inea/en/connecting-europe-facility">
                                <img src="${url.resourcesPath}/img/en_cef.jpg" class="responsive-img" alt="cef"></img>
                            </a>
                        </div>
                        <div class="col s12  m4 footerCol" style="padding-left: 5em;">
                            <p style="margin-top: 0;text-align: justify; color: black;">
                                The integration of this Service with the ESMO Network has been implemented by<a href="http://www.atlantis-group.gr/" target="_blank"> UAegean | i4m Lab</a>, and funded by the European Commission  (CEF Programme - <a href="http://esmo-project.eu/" target="_blank">ESMO project</a> | Agreement number: INEA/CEF/ICT/A2017/1451951)
                            </p>
                        </div>
                        <div class="col s12  m4 footerCol" style="padding-left: 5em; margin-top: 0.2%;">
                            <a target="_blank" href="//opensource.org"><img  src="${url.resourcesPath}/img/open_source1-4.png" class="responsive-img" alt="cef"/></a>
                            <p style="margin:0">Developed with Open Source Software (OSS) </p>
                        </div>
        
                    </div>
                </div>
        
        
            </footer>

        </div>
        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script>
        <script th:inline="javascript">
                                    /*<![CDATA[*/
                                    $(document).ready(function () {
                                        $('select').material_select();
                                        $('.modal').modal();
                                        if (!$('#countrySelection').val()) {
                                            $('#next').removeClass("waves-effect waves-light submit").addClass('disabled');
                                        }

                                        $('#countrySelection').change(function () {
                                            if (this.vaue !== "") {
                                                $('#next').removeClass("disabled").addClass('waves-effect waves-light submit');
                                            } else {
                                                $('#next').removeClass("waves-effect waves-light submit").addClass('disabled');
                                            }

                                        });
                                    });

                                    function onHomeClick() {
                                        let sessionId = document.getElementById('sessionId').value;
                                        window.location = "/ap/proceedAfterError?sessionId=" + sessionId;
                                    }


                                    /*]]>*/
        </script>
    </body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <link href="styles.css" rel="stylesheet" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Chatty</title>
</head>

<body>
<div id = "block-content">

    <div id = "block-nigger">

        <div id = "Registration-block">
            <a>Register for nachalo!</a>
            <label>Username:</label><input id = "username" type="text" size="40">
            <label>Firstname:</label><input id = "firstname" type="text" size="40">
            <label>Lastname:</label><input id = "lastname" type="text" size="40">
            <button id = "reg">Register</button>
        </div>

        <div id = "Login-block">
            <a>Or if you imeesh' ack to, login for nachalo :3</a>
            <label>Username:</label> <input id = "loginname" type="text" size="40">
            <button id = "login">Login</button>
            <%--<button id = "findall">HANTN</button>--%>
            <%--<button id = "gofuckyourself" onclick="addRow('myTable');return false;">GO!</button>--%>
        </div>

    </div>
    <div id = "block-chat">

        <table id="myTable" cellspacing="0" border="1">
            <tbody>
            <tr>
                <td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td>
            </tr>
            </tbody>
        </table>

    </div>
    <div id = "block-message">
        <div id = "block-send-message">
            <%--<p><input id = "name" type="text" width="15" placeholder="Username:"> </p>--%>
            <input id = "text" type="text" width="15" placeholder="Your message:"><button id = "send">Send</button><button id = "findall">Update</button>
        </div>

    </div>
    <div id = "block-file">
        <div id = "block-send-file">

                <input type="file" id="input-file"/><input placeholder="Your comment:" type="text" id="input-comment"/><input type="submit" value="Send" id="send-file">
        </div>
    </div>
</div>


<%--suppress JSAnnotator --%>
<script>

    var usernameValue;
    var rootURL = "http://localhost:8080/api/messages";
    var messageId;
    var messagetext;
    var created;
    var author;
    var file = "";
    var namevalue;
    var clickcount = 0;
    var profileId;
    var profileUsername;
    var profileFirstname;
    var profileLastname;
    var currentUser = "NotLoginnedRetard";
    var fakepath = "D:\\";

    var fileName;
    var fileComment;


    //ЭТО ТОЖЕ ДОЛЖНО РАБОТАТЬ, НО НЕТЬ :С//
//
//    $("#send-file").on('click', function(e) {
//        upload();
//    });
//
//
//    function upload(){
//        var formData = new FormData();
//        var obj = $('#input-file').get(0).files[0];
//        formData.append('file',obj,obj.name);
//        alert(obj.name);
//        $.ajax({
//            type: "POST",
//            url: "/api/files/upload",
//            data: formData,
//            cache: false,
//            contentType: false,
//            processData: false,
//            success: function() {
//                alert("File uploaded!");
//            },
//            error: function(jqXHR, textStatus, errorThrown) {
//                console.log('There was a ' + textStatus + ": " + errorThrown);
//            }
//        });
//    }

    $("#input-file").change(function (){
            //fileName = $(this).val();
            fileName = fakepath + document.getElementById("input-file").files[0].name;

            $("#wrap").html(fileName);
        });
    $( "#send-file" ).click(function() {
        var nameValue = currentUser;
        fileComment = document.getElementById("input-comment").value;
        fileName = document.getElementById("input-file").files[0].name;
        var myURL = document.location;
        document.location = "http://localhost:8080" +"/api/files/send"+ "?file="+fileName+"&message="+fileComment+"&name="+nameValue;
        alert("File has been uploaded!");
    });
    $( "#send" ).click(function() {
        var nameValue = currentUser;
        var textValue = $("#text").val();
        var myURL = document.location;
        document.location = "http://localhost:8080" +"/api/messages/send"+ "?name="+nameValue+"&text="+textValue;

    });
    $( "#reg" ).click(function() {
        usernameValue = $("#username").val();
        var firstnameValue = $("#firstname").val();
        var lastnameValue = $("#lastname").val();
        var myURL = document.location;
        currentUser = usernameValue;
        document.location = "http://localhost:8080" +"/api/profiles/reg"+ "?username="+usernameValue+"&firstname="+firstnameValue+"&lastname="+lastnameValue;
        alert("Registration successful");
    });
    $( "#findbyid" ).click(function() {
//        console.log('findById: ' + id);
        $.ajax({
            type: 'GET',
            url: rootURL + '/' + 1,
            dataType: "json",
            success: function(data){
                currentMessage = data;
                renderDetails(currentMessage);
            }
        });
    });
    $( "#findall" ).click(function() {
//        console.log('findById: ' + id);
        $.ajax({
            type: 'GET',
            url: rootURL,
            dataType: "json",
            success: function(data){

                deleteRows("myTable");
                for(var i=0;i<data.length;i++)
                {
                    currentMessage = data[i];
                    renderDetails(currentMessage);
                }

            }
        });
    });
    $( "#login" ).click(function() {
        namevalue = $("#loginname").val();
        //document.location = "http://localhost:8080/api/profiles/getUsername"+"?username="+namevalue;
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/api/profiles',
            dataType: "json",
            success: function(data){
                var i1 = 0;
                for(var i=0;i<data.length;i++)
                {
                    currentProfile = data[i];
                    renderProfileDetails(currentProfile);
                    if(profileUsername == namevalue)
                    {i1=1;
                    break;}
                    else
                    {i1=0;}
                }

                if(i1==1)
                {
                    currentUser = namevalue;
                    alert("You are logined as "+currentUser);
                }
                else
                {
                    currentUser = "NotLoginnedRetard";
                    alert("You are bad, so bad!");
                }
            }
        });
    });
    function renderProfileDetails(profile) {
        profileId = profile.id;
        profileUsername = profile.profileName;
        profileFirstname = profile.firstName;
        profileLastname = profile.lastName;
    }
    function renderDetails(message) {
        messageId = message.id;
        messagetext = message.message;
        created = message.created;
        author = message.author;
        file = message.filename;
        addRow("myTable", messageId, created, author, messagetext,file);
    }
    function addRow(id,messageId, created, username, message, file){
        var tbody = document.getElementById(id).getElementsByTagName("TBODY")[0];
        var row = document.createElement("TR");


        var btnShowU = document.createElement("INPUT");

        btnShowU.setAttribute("type","button");
        btnShowU.setAttribute("value","Update");
        btnShowU.setAttribute("onclick","selectRows(this)");



        var btnD = document.createElement("INPUT");
        btnD.setAttribute("type","button");
        btnD.setAttribute("value","Delete");
        btnD.setAttribute("onclick","deleteRow(this)");

        var updateInput = document.createElement("INPUT");
        updateInput.setAttribute("id","input");
        updateInput.setAttribute("type","text");
        updateInput.setAttribute("disabled","disabled");
        updateInput.setAttribute("value",message);

        var a4 = document.createElement("a");
        a4.setAttribute("id","download-link");
        a4.setAttribute("href", "D:/Chatty/src/main/resources/files/"+file);
        a4.setAttribute("download", file);
        //a4.setAttribute("value", file);
        if (file != null)
        {a4.text = file;}
        else
        {a4.text = "";}



        var td0 = document.createElement("TD");
        td0.setAttribute("id",messageId);
        td0.appendChild(document.createTextNode(messageId));

        var td1 = document.createElement("TD");
        td1.setAttribute("id",messageId);
        td1.appendChild(document.createTextNode(created));

        var td2 = document.createElement("TD");
        td2.setAttribute("id",messageId);
        td2.appendChild (document.createTextNode(username));

        var td3 = document.createElement("TD");
        td3.setAttribute("id",messageId);
        td3.appendChild (document.createTextNode(message));

        var td4 = document.createElement("TD");
        td4.setAttribute("id",messageId);
        td4.appendChild(a4);

        /*Сворганить ссылку на файл, т.е. переделать значение */



        var td5 = document.createElement("TD");
        td5.setAttribute("id",messageId);
        td5.appendChild(btnShowU);
//        td4.appendChild(btnU);
        var td6 = document.createElement("TD");
        td6.setAttribute("id",messageId);
        td6.appendChild (btnD);

        row.appendChild(td0);
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        row.appendChild(td6);
        tbody.appendChild(row);
    }
    function deleteRow(r){
        var i=r.parentNode.parentNode.rowIndex;

        var x0 = document.getElementById('myTable').rows[i].cells[0].innerHTML;
        var x3 = document.getElementById('myTable').rows[i].cells[3].innerHTML;
        var myURL = document.location;
        document.location = "http://localhost:8080" +"/api/messages/removemessage"+ "?id="+x0;

        alert("Message: "+x3+" was succesfully removed!");

        document.getElementById('myTable').deleteRow(i);
        //deleteRows();
    }
    var table = document.getElementById('myTable');
    var editingTd;
/*    table.onclick = function(event) {

        var target = event.target;

        while (target != table) {
            if (target.className == 'edit-cancel') {
                finishTdEdit(editingTd.elem, false);
                return;
            }

            if (target.className == 'edit-ok') {
                finishTdEdit(editingTd.elem, true);
                return;
            }

            if (target.nodeName == 'TD') {
                if (editingTd) return; // already editing

                makeTdEditable(target);
                return;
            }

            target = target.parentNode;
        }
    };
    /*function makeTdEditable(td) {

        editingTd = {
            elem: td,
            data: td.innerHTML
        };

        td.classList.add('edit-td'); // td, not textarea! the rest of rules will cascade

        var textArea = document.createElement('textarea');
        textArea.style.width = td.clientWidth + 'px';
        textArea.style.height = td.clientHeight + 'px';
        textArea.className = 'edit-area';

        textArea.value = td.innerHTML;
        td.innerHTML = '';
        td.appendChild(textArea);
        textArea.focus();

        td.insertAdjacentHTML("beforeEnd",
            '<div class="edit-controls"><button  id = "updateOK" class="edit-ok">OK</button><button class="edit-cancel">CANCEL</button></div>'
        );
    }
    function finishTdEdit(td, isOk) {
        if (isOk) {
            td.innerHTML = td.firstChild.value;
        } else {
            td.innerHTML = editingTd.data;
        }
        td.classList.remove('edit-td'); // remove edit class
        editingTd = null;
    }*/
    function deleteRows(id) {
        var tbl = document.getElementById("myTable");
        var trs = tbl.rows.length;
        for(var j=1; j<trs; j++){
                tbl.deleteRow(1);
        }
    }
    function selectRows(r){

        var i=r.parentNode.parentNode.rowIndex;
        var x0 = document.getElementById('myTable').rows[i].cells[0].innerHTML;
        var x1 = document.getElementById('myTable').rows[i].cells[1].innerHTML;
        var x2 = document.getElementById('myTable').rows[i].cells[2].innerHTML;
        var x3 = document.getElementById('myTable').rows[i].cells[3].innerHTML;
        var x4 = document.getElementById('myTable').rows[i].cells[4].innerHTML;
        var myURL = document.location;
        document.location = "http://localhost:8080" +"/api/messages/update"+ "?id="+x0+"&author="+x2+"&message="+x3+"&message="+x4;

        alert("Message: "+x3+" was succesfully updated!");


    }
</script>
</body>
</html>
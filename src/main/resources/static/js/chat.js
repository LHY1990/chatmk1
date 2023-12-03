const MY_MESSAGE = "my-message"
const OTHER_MESSAGE = "other-message"
const BSY = "bsy"
const LHY = "lhy"
const SEPERATOR =":"

window.onload = function(){
    //소켓 연결 부분
    const socket = new WebSocket("ws://192.168.96.1:8080/chatSocket")
    //const socket = new WebSocket("ws://localhost:8080/chatSocket")
    socket.onopen = function(e){
        console.log("소켓 연결이 잘됌")
    }
    //메세지를 받은 경우
    socket.onmessage = async function(event){
        try{
            if (event!=null && event!=undefined)
            {
                console.log(event.data)
                seperatorIndex = event.data.search(SEPERATOR)
                
                const nickname = event.data.substr(0, seperatorIndex)
                const message = event.data.substr(seperatorIndex+1)
                const myUserId = document.getElementById("user-id").value

                if (myUserId!=nickname)
                {
                    //alert("myUserId : "+myUserId)
                    //alert("nickname : "+nickname)
                    insertMessage(OTHER_MESSAGE, message)
                }
            }
        }catch(error)
        {
            console.log(error)
            console.log("웹소켓 에러남")
        }
    }
    socket.onclose = function(){
        console.log("소켓 연결이 끊어짐")
    }
}

function sendMessage(){            
    textBox = document.getElementById("messageBox");
    const pureMassage = textBox.value
    
    myUserId = document.getElementById("user-id").value
    serverSideMessage = myUserId+SEPERATOR+textBox.value
    textBox.value = ""

    $.ajax({
        type:"GET",  
        url: "/send", // json 넣어놨음
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data:
        {
            "userId" : myUserId,
            "message" : serverSideMessage
        },
        success: function (response){
            //내게 보일것
            insertMessage(MY_MESSAGE, pureMassage)
        },
        error: function(error){
            console.log("메세지 보낼때 문제있음");
        }
    })


}

function insertMessage(className, message)
{
    //붙일곳
    textBoxElement = document.getElementById("chat-body");

    //div만들고 p만든뒤p에 글자넣고 div에 종속 시킨뒤 위 chat-body에 붙인다.
    divElement = document.createElement("div")
    pElement = document.createElement("p")
    pElement.innerText = message
    divElement.appendChild(pElement)

    divElement.setAttribute("class", className)

    textBoxElement.appendChild(divElement)

    textBoxElement.scrollTop = textBoxElement.scrollHeight
}
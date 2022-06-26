let stompClient = null;

function connect() {
	let socket = new SockJS("/server1")

	stompClient = Stomp.over(socket)

	stompClient.connect({}, function(frame) {


		$('#name-form').addClass('d-none')
		$('#chat-room').removeClass('d-none')

		stompClient.subscribe("/topic/return-to", function(res) {
			showMessage(JSON.parse(res.body))
		})
	})
}


function showMessage(jsonres) {

	$('#message-container-table').prepend(`<tr><td><b>${jsonres.name}: </b>${jsonres.content} </td></tr>`)

}

function sendMessage() {
	let jsonObj = {
		name: localStorage.getItem("name"),
		content: $("#message-value").val()
	}

	stompClient.send("/app/message", {}, JSON.stringify(jsonObj));
}

$(document).ready((e) => {


	$('#login').click(() => {

		let name = $("#name-value").val()
		localStorage.setItem("name", name);
		$('#name-title').html(`Welcome,<b>${name}</b>`)

		connect()

	})
	$('#send-btn').click(() => {
		sendMessage()
	})
	$("#logout").click(() => {
		localStorage.removeItem("name")
		if (stompClient !== null) {
			stompClient.disconnect()

			$('#name-form').removeClass('d-none')
			$('#chat-room').addClass('d-none')
		}
	})


})
function formatBankAccount(data) {
	return 	'<div class="row panel-body-row">'
			+	'<div class="col-sm-3 label-text">' + data.nickname + '</div>'
			+	'<div class="col-sm-9 content-text">'
			+		'<div class="row">'
			+			'<div class="col-sm-9">'
			+				'<div class="row panel-body-row-2">'
			+					'<div class="col-sm-4 label-text-2">AccountType</div>'
			+					'<div class="col-sm-8 content-text">'+ data.accountType.description + '</div>'
			+				'</div>'
			+				'<div class="row panel-body-row-2">'
			+					'<div class="col-sm-4 label-text-2">Routing Number</div>'
			+					'<div class="col-sm-8 content-text">' + data.routingNumber + '</div>'
			+				'</div>'
			+				'<div class="row panel-body-row-2">'
			+					'<div class="col-sm-4 label-text-2">Account Number</div>'
			+					'<div class="col-sm-8 content-text">' + data.accountNumber + '</div>'
			+				'</div>'
			+			'</div>'
			+			'<div class="col-sm-3 text-center panel-body-icon">'
			+				'<a data-toggle="modal" data-target="#bank-modal" data-purpose="edit" class="edit-bank-account-icon"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>'
			+				'<a data-toggle="modal" data-target="#bank-confirm" class="delete-bank-account-icon"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>'
			+			'</div>'
			+		'</div>'
			+	'</div>'
			+'</div>'
}

$(document).ready(function() {
	$.ajax({
        type : "GET",
        url : bankInfoUrl + "/" + userEmployeeId,
    }).done(function(data) {
        $.each(data, function(key, account) {
			var newAccount = $.parseHTML(formatBankAccount(account));
			$("#bank-account-panel .panel-body").prepend(newAccount);
			$(".edit-bank-account-icon", newAccount).data(account);

			var option = $("<option></option>");
			option.val(account.accountId).text(account.nickname);
			$("#edit-paychecks-form select").append(option);
		});
    }).fail(function(data) {
        alert("Get bank accounts failed.");
    });

	var $modal = $("#bank-modal");
	$modal.on("show.bs.modal", function(e) {
        var $button = $(e.relatedTarget);
        var account = $button.data();
        var purpose = $button.data("purpose");

        if (purpose == "edit") {
        	$("input[name='accountNickname']", $modal).val(account.nickname);
        	$("input[value='" + account.accountType.id + "']", $modal).prop("checked", true);
        	$("input[name='routingNumber']", $modal).val(account.routingNumber);
        	$("input[name='accountNumber']", $modal).val(account.accountNumber);
        } else if (purpose == "add") {
        	$("#bank-modal").find("form")[0].reset();
        } else {
        	alert(" Wrong button clicked! ");
        }
    });

    $("#edit-paychecks-icon, #cancel-edit-paychecks").click(function() {
    	$("#edit-paychecks-form").toggleClass("hide");
    	$("#paychecks-display").toggleClass("hide");
    });

    $("input [name='payment-method']").click(function(e) {
    	console.log(e.target);
    })
});
function formatBankAccount(data) {
	return 	'<div class="row panel-body-row">'
			+	'<div class="col-sm-3 label-text">' + data.nickname + '</div>'
			+	'<div class="col-sm-9 content-text">'
			+		'<div class="row">'
			+			'<div class="col-sm-9">'
			+				'<div class="row panel-body-row-2">'
			+					'<div class="col-sm-4 label-text-2">AccountType</div>'
			+					'<div class="col-sm-8 content-text">' + data.accountType.description + '</div>'
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

function formatPaycheckDistribution(data) {
	return  '<div class="row panel-body-row-2">'
		    +  '<div class="col-sm-4 label-text-2">' + data.nickname + ' ( ' + data.accountNumber + ' )' + '</div>'
			+  '<div class="col-sm-4 content-text">' + data.percent + '%</div>'
			+'</div>'
}

function formatEditPaycheckDistribution(data) {
	return  '<div class="row panel-body-row-2">'
			+  '<label class="control-label col-sm-3 label-text-2" for="' + data.accountId + '">' + data.nickname + '</label>'
			+  '<div class="col-sm-5">'
			+		'<div class="input-group">'
			+       	'<input type="number" name="' + data.accountId + '" class="form-control" placeholder="100%" maxlength="3" required />'
			+			'<span class="input-group-addon">%</span>'
			+		'</div>'
			+  '</div>'
			+'</div>'
}

function loadBankSection() {
	var bankAccounts = $("#bank-account-display").empty();
	var distr = $("#paycheck-distribution-display").empty();
	var distrEdit = $("#paycheck-distribution-edit").empty();
	var select = $("#paychecks-edit-form select");
	select.empty();
	bankAccounts.empty();
	distr.empty();
	distrEdit.empty();

	// populate payment method
	$.ajax({
        type : "GET",
        url : paychecksUrl + "/" + userEmployeeId,
    }).done(function(data) {
    	$("#payment-method-display").text(data.paymentMethod.description)
    }).fail(function(data) {
        alert("Get payment method failed.");
    });


	$.ajax({
        type : "GET",
        url : bankInfoUrl + "/" + userEmployeeId,
    }).done(function(data) {
        $.each(data, function(key, account) {
        	// populate banks accounts panel.
			var newAccount = $.parseHTML(formatBankAccount(account));
			bankAccounts.append(newAccount);
			$(".edit-bank-account-icon", newAccount).data("account", account);

			// populate paychecks panel
			if (account.percent > 0) {
				var newDistr = $.parseHTML(formatPaycheckDistribution(account));
				distr.append(newDistr);

				var newInput = $.parseHTML(formatEditPaycheckDistribution(account));
				distrEdit.append(newInput);
				$("input", newInput).val(account.percent);
			} else {
				var option = $("<option></option>");
				option.val(account.accountId).text(account.nickname);
				select.append(option);
			}
		});
    }).fail(function(data) {
        alert("Get bank accounts data failed.");
    });
}

$(document).ready(function() {

	loadBankSection();

	var $modal = $("#bank-modal");
	$modal.on("show.bs.modal", function(e) {
        var $button = $(e.relatedTarget);
        var account = $button.data("account");
        var purpose = $button.data("purpose");

        if (purpose == "edit") {
        	$("input[name='nickname']", $modal).val(account.nickname);
        	$("input[value='" + account.accountType.id + "']", $modal).prop("checked", true);
        	$("input[name='routingNumber']", $modal).val(account.routingNumber);
        	$("input[name='accountNumber']", $modal).val(account.accountNumber);
        	$("input[name='accountNumberConfirm']", $modal).val("");
        	$modal.data("accountId", account.accountId);
        } else if (purpose == "add") {
        	$("#bank-modal").find("form")[0].reset();
        } else {
        	alert(" Wrong button clicked! ");
        }
    });

    $("#edit-paychecks-icon, #cancel-edit-paychecks").click(function() {
    	$("#paychecks-edit-form").toggleClass("hide");
    	$("#paychecks-display").toggleClass("hide");
    });

    $("input[type='radio'][name='payment-method']").change(function(e) {
    	if ($(e.target).val() == "PC") {
    		$("#edit-paycheck-distribution-wrapper").hide();
    	} else {
    		$("#edit-paycheck-distribution-wrapper").show();
    	}

    });

    $("#bank-account-form").submit(function( event ) {
    	event.preventDefault();
    	var $form = $( this );
    	var $accountId = $modal.data("accountId");
    	console.log($accountId);
    	if ($accountId == null) {
			$.ajax({
			   	type: "POST",
			  	url: bankInfoUrl + "/" + userEmployeeId,
			   	data: $form.serialize(),
			   	beforeSend: function(xhr) {
					xhr.setRequestHeader(header, token);
			   	}
			}).done(function(data) {
				// $("#banking").load(location.href+" #banking>*","");
				if (data.error != null) {
					alert(data.error);
				} else {
					$modal.modal('hide');
					loadBankSection();
				}
			}).fail(function(data) {
				alert(data);
			});
    	} else {
    		$.ajax({
			   	type: "PUT",
			  	url: bankInfoUrl + "/" + userEmployeeId + "/" + $accountId,
			   	data: $form.serialize(),
			   	beforeSend: function(xhr) {
					xhr.setRequestHeader(header, token);
			   	}
			}).done(function(data) {
				// $("#banking").load(location.href+" #banking>*","");
				if (data.error != null) {
					alert(data.error);
				} else {
					$modal.modal('hide');
					loadBankSection();
				}
			}).fail(function(data) {
				alert(data);
			});
    	}
    });
});
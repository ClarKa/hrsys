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
	return '<div class="row panel-body-row-2">'
			+	'<div class="col-sm-4 label-text-2">' + data.nickname + ' ( ' + data.accountNumber + ' )' + '</div>'
			+	'<div class="col-sm-8 content-text">' + data.percent + '%</div>'
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

			if (account.percent > 0) {
				var node = $.parseHTML(formatPaycheckDistribution(account));
				$("#paycheck-distribution-display").append(node);
			}

			var option = $("<option></option>");
			option.val(account.accountId).text(account.nickname);
			$("#edit-paychecks-form select").append(option);
		});
    }).fail(function(data) {
        alert("Get bank accounts failed.");
    });

	$.ajax({
        type : "GET",
        url : paychecksUrl + "/" + userEmployeeId,
    }).done(function(data) {
    	$("#payment-method-display").text(data.paymentMethod.description)
    }).fail(function(data) {
        alert("Get paychecks failed.");
    });

    var handle = $( "#slider-value" );
    $( "#slider" ).slider({
      create: function() {
        handle.text( $( this ).slider( "value" ) + "%");
      },
      slide: function( event, ui ) {
        handle.text( ui.value + "%");
        handle2.text( 100-ui.value + "%");
        $( "#slider2" ).slider("value", 100-ui.value);
      }
    });

    var handle2 = $( "#slider2-value" );
    $( "#slider2" ).slider({
      create: function() {
        handle2.text( $( this ).slider( "value" ) + "%");
      },
      slide: function( event, ui ) {
        handle2.text( ui.value + "%");
        handle.text( 100-ui.value + "%");
        $( "#slider" ).slider("value", 100-ui.value);
      }
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

    $("input[type='radio'][name='payment-method']").change(function(e) {
    	if ($(e.target).val() == "PC") {
    		$("#edit-paycheck-distribution").hide();
    	} else {
    		$("#edit-paycheck-distribution").show();
    	}

    })
});
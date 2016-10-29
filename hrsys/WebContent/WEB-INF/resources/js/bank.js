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
			+				'<a data-toggle="modal" data-target="#delete-bank-confirm-modal" class="delete-bank-account-icon"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>'
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
	return  '<div class="row panel-body-row-2 paycheck-distribution-entry">'
			+  '<label class="control-label col-sm-2 label-text-2" for="' + data.accountId + '">' + data.nickname + '</label>'
			+  '<div class="col-sm-5">'
			+		'<div class="input-group">'
			+       	'<input type="number" name="' + data.accountId + '" class="form-control" placeholder="100%" maxlength="3" required />'
			+			'<span class="input-group-addon">%</span>'
			+		'</div>'
			+  '</div>'
			+  '<div class="col-sm-1 control-label panel-body-icon">'
			+      '<a class="delete-paycheck-distribution-icon"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span></a>'
			+  '</div>'
			+'</div>'
}

var editDistribution = {
	data: [],
	initialize: function (accounts) {
		$this = this;
		this.data = [];
		$.each(accounts, function(key, account) {
			if (account.percent > 0) {
				// editDistribution.data.push(account);

				var container = $("#paycheck-distribution-edit");
				var newInput = $.parseHTML(formatEditPaycheckDistribution(account));
				// container.append(newInput);
				// $("input", newInput).val(account.percent);
				// $(".delete-paycheck-distribution-icon", newInput).click(function() {
				// 	$(newInput).hide();
				// });

				var newEntry = {
					accountId: account.accountId,
					nickname: account.nickname,
					percent: account.percent,
					input: newInput
				}
				$this.data.push(newEntry);
			}
		});

		this.render();
	},
	render: function() {
		var container = $("#paycheck-distribution-edit");
		$this = this;

		container.empty();
	 	$.each(this.data, function(key, entry) {
	 		var newInput = entry.input;

			container.append(newInput);
			$("input", newInput).val(entry.percent);
			$(".delete-paycheck-distribution-icon", newInput).click(function() {
				$this.remove(entry.accountId);
			});
		});
	},
	remove: function(id) {
		var index = this.data.map(function(x) { return x.accountId; }).indexOf(parseInt(id));
		if( index > -1) {
			this.data.splice(index, 1);
			this.render();
		}
	},
	addToData: function(id, nickname) {
		var index = this.data.map(function(x) { return x.accountId; }).indexOf(parseInt(id));

		if ( index == -1 ) {
			var account = {
				accountId: id,
				nickname: nickname
			}

			var entry = {
				accountId: parseInt(id),
				nickname: nickname,
				percent: 0,
				input: $.parseHTML(formatEditPaycheckDistribution(account))
			}

			this.data.push(entry);
			this.render();
		} else {
			var input = this.data[index].input;
			$(input).show();
		}
	}
}

function loadBankSection() {
	var bankAccounts = $("#bank-account-display").empty();
	var distr = $("#paycheck-distribution-display").empty();
	var select = $("#paychecks-edit-form select");
	select.empty();
	bankAccounts.empty();
	distr.empty();

	// populate payment method
	$.ajax({
        type : "GET",
        url : paychecksUrl + "/" + userEmployeeId,
    }).done(function(data) {
    	if (data.error != null) {
    		return;
    	}
    	$("#payment-method-display").text(data.paymentMethod.description);
    	$("#paychecks-edit-form input[value='" + data.paymentMethod.id + "']").prop("checked", true);
    	if (data.paymentMethod.id == "pc") {
    		$("#edit-paycheck-distribution-wrapper").hide();
    		$("#paycheck-distribution-display-wrapper").hide();
    	} else {
    		$("#edit-paycheck-distribution-wrapper").show();
    		$("#paycheck-distribution-display-wrapper").show();
    	}
    }).fail(function(data) {
        alert("Get payment method failed.");
    });


	$.ajax({
        type : "GET",
        url : bankInfoUrl + "/" + userEmployeeId,
    }).done(function(data) {
    	if (data.error != null) {
    		return;
    	}
        $.each(data, function(key, account) {
        	var accountId = account.accountId;
        	// populate banks accounts panel.
			var newAccount = $.parseHTML(formatBankAccount(account));
			bankAccounts.append(newAccount);
			$(".edit-bank-account-icon", newAccount).data("account", account);
			$(".delete-bank-account-icon", newAccount).data("accountId", accountId);

			// populate paychecks panel
			if (account.percent > 0) {
				var newDistr = $.parseHTML(formatPaycheckDistribution(account));
				distr.append(newDistr);
			}

			// populate select list
			var option = $("<option></option>");
			option.val(account.accountId).text(account.nickname);
			select.append(option);
		});

        editDistribution.initialize(data);

    	$("#paychecks-edit-form").hide();
    	$("#paychecks-display").show();
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
        	$modal.data("accountId", -1);
        } else {
        	alert(" Wrong button clicked! ");
        }
    });

	var $deleteModal = $("#delete-bank-confirm-modal");
    $deleteModal.on("show.bs.modal", function(e) {
    	var $button = $(e.relatedTarget);
    	var accountId = $button.data("accountId");
    	$(this).data("accountIdToDelete", accountId);
    });

    $("#delete-bank-confirm-button").click(function(e) {
    	var accountIdToDelete = $deleteModal.data("accountIdToDelete");
    	$.ajax({
			   	type: "DELETE",
			  	url: bankInfoUrl + "/" + userEmployeeId + "/" + accountIdToDelete,
			   	beforeSend: function(xhr) {
					xhr.setRequestHeader(header, token);
			   	}
			}).done(function(data) {
				if (data.error != null) {
					alert(data.error);
				} else {
					$deleteModal.modal('hide');
					loadBankSection();
				}
			}).fail(function(data) {
				alert(data);
			});
    });

    $("#edit-paychecks-icon, #cancel-edit-paychecks").click(function() {
    	$( "#paychecks-display, #paychecks-edit-form" ).toggle();
    });

    $("#add-paycheck-distribution-icon").click(function() {
    	var accountId = $("#paychecks-edit-form select option:selected").val();
    	var nickname = $("#paychecks-edit-form select option:selected").text();
    	editDistribution.addToData(accountId, nickname);
    });

    $("input[type='radio'][name='paymentMethod']").change(function(e) {
    	if ($(e.target).val() == "pc") {
    		$("#edit-paycheck-distribution-wrapper").hide();
    	} else {
    		$("#edit-paycheck-distribution-wrapper").show();
    	}
    });

    $("#bank-account-form").submit(function( event ) {
    	event.preventDefault();
    	var $form = $( this );
    	var $accountId = $modal.data("accountId");

    	if ($accountId == null || $accountId == -1) {
			$.ajax({
			   	type: "POST",
			  	url: bankInfoUrl + "/" + userEmployeeId,
			   	data: $form.serialize(),
			   	beforeSend: function(xhr) {
					xhr.setRequestHeader(header, token);
			   	}
			}).done(function(data) {
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

	$("#paychecks-edit-form").submit(function( event ) {
		event.preventDefault();
		var $form = $( this );

		$(":hidden input", $form).val(0);
		console.log($form.serialize());

		$.ajax({
			   	type: "PUT",
			  	url: paychecksUrl + "/" + userEmployeeId,
			   	data: $form.serialize(),
			   	beforeSend: function(xhr) {
					xhr.setRequestHeader(header, token);
			   	}
			}).done(function(data) {
				if (data.error != null) {
					alert(data.error);
				} else {
					loadBankSection();
				}
			}).fail(function(data) {
				alert(data);
			});
	});
});
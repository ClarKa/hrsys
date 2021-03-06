<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="<c:url value='/resources/js/bank.js' />"></script>
<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="row">
				<div class="col-xs-6">Paychecks</div>
				<div class="col-xs-6 panel-heading-icon">
					<a id="edit-paychecks-icon">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</a>
				</div>
			</div>
		</div>

		<div class="panel-body">
			<div id="paychecks-display">
				<div class="row panel-body-row">
					<div class="col-sm-3 label-text">Payment Method</div>
					<div class="col-sm-9 content-text" id="payment-method-display"></div>
				</div>
				<div class="row panel-body-row" id="paycheck-distribution-display-wrapper">
					<div class="col-sm-3 label-text">Paycheck Distribution</div>
					<div class="col-sm-9 content-text" id="paycheck-distribution-display"></div>
				</div>
			</div>

			<form class="form-horizontal" role="form" id="paychecks-edit-form">
				<div class="form-group">
					<label class="control-label col-sm-4" for="paymentMethod"> Payment Method </label>
					<div class="col-sm-8">
						<label class="radio-inline"> <input type="radio" name="paymentMethod" value="dd"> Direct Deposit
						</label> <label class="radio-inline"> <input type="radio" name="paymentMethod" value="pc"> Paper Check
						</label>
					</div>
				</div>
				<div class="form-group" id="edit-paycheck-distribution-wrapper">
					<label class="control-label col-sm-4">Paycheck Distribution</label>
					<div class="col-sm-8 content-text">
						<div id="paycheck-distribution-edit">
						<!-- <div class="row panel-body-row-2">
							<label class="control-label col-sm-2 label-text-2" for="bankpercent"> PNC </label>
							<div class="col-sm-8">
								<input type="number" name="bankpercent" class="form-control" placeholder="100%" maxlength="3" required />
							</div>
						</div> -->
						</div>
						<br>
						<div class="row panel-body-row-2">
							<div class="col-sm-2"></div>
							<div class="col-sm-5">
								<select class="form-control">
								</select>
								<small class="text-muted">Select to add an account to distribution.</small>
							</div>
							<div class="panel-body-icon control-label col-sm-1">
								<a id="add-paycheck-distribution-icon"><span class="glyphicon 		glyphicon-plus" aria-hidden="true"></span></a>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<div class="form-group">
					<div class="col-sm-10 text-right">
						<button type="button" class="btn btn-default" id="cancel-edit-paychecks">Cancel</button>
						<input type="Submit" class="btn btn-primary" value="Save" />
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<div>Bank Accounts</div>
		</div>

		<div class="panel-body">
			<div id="bank-account-display"></div>
			<hr>
			<div class="panel-body-icon">
				<a data-toggle="modal" data-target="#bank-modal" data-purpose="add">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Add Bank Account
				</a>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" tabindex="-1" role="dialog" id="bank-modal">
	<div class="modal-dialog" role="dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title"></h4>
			</div>
			<form class="form-horizontal" role="form" id="bank-account-form">
				<div class="modal-body">
					<div class="form-group">
						<label class="control-label col-sm-5" for="nickname"> Account Nickame <span class="glyphicon glyphicon-question-sign"
							aria-hidden="true" data-toggle="tooltip" title="Any name helps you identify your bank account."></span></label>
						<div class="col-sm-6">
							<input type="text" name="nickname" class="form-control" placeholder="Maximum length is 20 characters" maxlength="20" required />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5" for="accountType"> Account Type </label>
						<div class="col-sm-6">
							<label class="radio-inline"> <input type="radio" name="accountType" value="C"> Checking
							</label> <label class="radio-inline"> <input type="radio" name="accountType" value="S"> Saving
							</label>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5" for="routingNumber"> Routing Number </label>
						<div class="col-sm-6">
							<input type="text" name="routingNumber" class="form-control" required />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5" for="accountNumber"> Account Number </label>
						<div class="col-sm-6">
							<input type="text" name="accountNumber" class="form-control" required />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5" for="accountNumberConfirm"> Re-enter Account Number </label>
						<div class="col-sm-6">
							<input type="text" name="accountNumberConfirm" class="form-control" required />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" class="btn btn-primary" />
				</div>
			</form>
		</div>
	</div>
</div>

<div class="modal fade" tabindex="-1" role="dialog" id="delete-bank-confirm-modal">
	<div class="modal-dialog" role="dialog">
		<div class="modal-content">
			<div class="modal-body">
				<h4 class="delete-confirm text-center">Are you sure you want to delete this account?</h4>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
				<button type="button" class="btn btn-primary" id="delete-bank-confirm-button">Yes</button>
			</div>
		</div>
	</div>
</div>
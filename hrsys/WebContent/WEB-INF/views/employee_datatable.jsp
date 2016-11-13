<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value='/resources/DataTables-1.10.12/css/dataTables.bootstrap.min.css' />"  rel="stylesheet">
<script src="<c:url value='/resources/DataTables-1.10.12/js/jquery.dataTables.min.js' />"></script>
<script src="<c:url value='/resources/DataTables-1.10.12/js/dataTables.bootstrap.min.js' />"></script>

<style>
td.details-control {
    background: url('resources/img/details_open.png') no-repeat center center;
    cursor: pointer;
}
tr.shown td.details-control {
    background: url('resources/img/details_close.png') no-repeat center center;
}

td.delete-control {
    background: url('resources/img/delete.png') no-repeat center center;
    cursor: pointer;
}

#details-table td:nth-child(odd) {
    font-style: italic;
    font-weight: bold;
}

#details-table td:nth-child(even) {
    text-align: center;
}

#details-table {
    width: 90% !important;
    margin: auto !important;
}

</style>

<script>
/* Formatting function for row details */
function format ( d ) {
    // `d` is the original data object for the row
    return '<table id="details-table" class="table" cellpadding="3" cellspacing="0" style="margin:10px;">'+
        '<tr>'+
            '<td class="col-sm-2">Gender:</td>'+
            '<td class="col-sm-4">'+d.gender+'</td>'+
            '<td class="col-sm-2">Birthday:</td>'+
            '<td class="col-sm-4">'+d.birth+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>SSN:</td>'+
            '<td>'+d.ssn+'</td>'+
            '<td>Marriage:</td>'+
            '<td>'+d.marriage+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Nationality:</td>'+
            '<td>'+d.nationality+'</td>'+
            '<td>Education:</td>'+
            '<td>'+d.education+'</td>'+
        '</tr>'+
        '<tr>'+
	        '<td>Enrollment Date:</td>'+
	        '<td>'+d.enrollmentDate+'</td>'+
	        '<td>Position:</td>'+
	        '<td>'+d.position+'</td>'+
	    '</tr>'+
		'<tr>'+
	        '<td>Phone Number:</td>'+
	        '<td>'+d.phone+'</td>'+
	        '<td></td>'+
            '<td></td>'+
	    '</tr>'+
        '<tr>'+
	        '<td>Account Username:</td>'+
	        '<td>'+d.username+'</td>'+
	        '<td>Account Authority:</td>'+
            '<td>'+d.role+'</td>'+
	     '</tr>'+
	    '<tr>'+
	        '<td colspan=4 class="text-center"><a href="#" class="btn btn-warning" data-toggle="modal" data-target="#employee-modal" data-purpose="edit" data-employeeid=' + d.employeeID + '> Edit </a></td>'+
	    '</tr>'+
    '</table>';
}

$(document).ready(function() {

    var table = $('#employee-datatable').DataTable( {
    	"ajax": {
    	    "url": "rest/employee",
    	    "dataSrc": ""
    	},
    	"rowId": "employeeID",
        "columns": [
            {
                "className": "details-control",
                "orderable": false,
                "data": null,
                "defaultContent": ""
            },
            {   "data": function(data) {
                return data.firstname + " " +data.lastname;
                }
            },
            {   "data": "email"},
            {   "data": "departmentName" },
            {
                "className": "delete-control",
                "orderable": false,
                "data": null,
                "defaultContent": ""
            }
        ],
        "order": [[1, 'asc']],
        responsive: true
    });

    // Add event listener for opening and closing details
    $('#employee-datatable-body').on('click', 'td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = table.row( tr );

        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            // Open this row
            row.child( format(row.data()) ).show();
            tr.addClass('shown');
        }
    });

    $('#employee-datatable-body').on('click', 'td.delete-control', function () {
    	var tr = $(this).closest('tr');
    	var row = table.row( tr );
    	var rowId = row.id();

    	$.ajax({
    		type: "DELETE",
    		url: employeeInfoUrl + "/" + rowId,
    		beforeSend: function(xhr) {
    			xhr.setRequestHeader(header, token);
    		}
    	}).done(function(data) {
    		if (data.error == null) {
    			row.remove().draw( false );
    		} else {
    			alert("Delete fail!");
    		}
    	}).fail(function() {
    		alert("Ajax request failed.");
    	});
    });

    $( "#employee-modal-form" ).submit(function( event ) {
        event.preventDefault();

        var $form = $( this );

        $.ajax({
            type: "POST",
            url: employeeModal.url,
            data: $form.serialize(),
            beforeSend: function(xhr) {
               xhr.setRequestHeader(header, token);
           }
        }).done(function(data) {
            var error = data.error;
            if (error == null) {
                toggleSuccessAlert();
                $("#employee-datatable").DataTable().ajax.reload();
                $('#employee-modal').modal('hide');
            } else {
                $(".alert-danger").text(error);
                toggleFailAlert();
            }
        }).fail(function(data) {
            toggleFailAlert();
        });
    });
});

$(document).ready(function() {
     // submit add/edit employee action

});

</script>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">Employee Information</div>
        <div class="panel-body">
            <div>
                <button class="btn btn-default" data-toggle="modal" data-target="#employee-modal" data-purpose="add">Add Employee</button>
                <button class="btn btn-default">Add User account</button>
            </div>
            <hr>
            <table id="employee-datatable" class="table table-hover table-bordered table-responsive" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th></th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Department</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody id="employee-datatable-body">
                </tbody>
            </table>
        </div>
    </div>
</div>


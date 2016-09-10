<link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css">
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>

<style>
td.details-control {
    background: url('resources/details_open.png') no-repeat center center;
    cursor: pointer;
}
tr.shown td.details-control {
    background: url('resources/details_close.png') no-repeat center center;
}

td.delete-control {
    background: url('resources/delete.png') no-repeat center center;
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
	        '<td>Phone:</td>'+
	        '<td>'+d.phone+'</td>'+
	        '<td></td>'+
            '<td><a href="#" class="btn btn-warning" data-toggle="modal" data-target="#employee-modal" data-purpose="edit" data-employeeid=' + d.employeeID + '> Edit </a></td>'
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
        "order": [[1, 'asc']]
    } );

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
    } );

    $('#employee-datatable-body').on('click', 'td.delete-control', function () {
    	var tr = $(this).closest('tr');
    	var row = table.row( tr );
    	var rowId = row.id();

        // include CSRF token.
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

    	$.ajax({
    		type: "DELETE",
    		url: "rest/employee/employeeid/" + rowId,
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
    } );
});
</script>

<table id="employee-datatable" class="table table-hover table-bordered" cellspacing="0" width="100%">
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
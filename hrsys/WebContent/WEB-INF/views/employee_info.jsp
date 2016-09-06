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
</style>

<script>
/* Formatting function for row details */
function format ( d ) {
    // `d` is the original data object for the row
    return '<table class="table table-hover" cellpadding="5" cellspacing="0" border="0" style="margin:10px;">'+
        '<tr>'+
            '<td>Email</td>'+
            '<td>'+d.email+'</td>'+
            '<td>Email</td>'+
            '<td>'+d.email+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Extension number:</td>'+
            '<td>'+d.lastname+'</td>'+
            '<td>Email</td>'+
            '<td>'+d.email+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Extra info:</td>'+
            '<td>'+d.email+'</td>'+
            '<td>Email</td>'+
            '<td>'+d.email+'</td>'+
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
    	
    	console.log("rest/employee/employeeid/" + rowId);
    } );

/*     $.ajax({
        type: "GET",
        url:"rest/employee"
    }).done(function(data) {
        for (var key in data) {
        	t.row.add( [
        	            null,
                        data[key].firstname + " " + data[key].lastname,
                        data[key].departmentName
        	        ] ).draw( false );
        }
    }).fail(function() {
        alert("Ajax failed to fetch data");
    }); */


});
</script>

<table id="employee-datatable" class="table table-hover table-bordered" cellspacing="0" width="100%">
    <thead>
        <tr>
            <th></th>
            <th>Name</th>
            <th>Email</th>
            <!-- <th>Gender</th>
            <th>Birthday</th>
            <th>SSN</th>
            <th>Marriage</th>
            <th>Nationality</th>
            <th>Education</th>
            <th>Enrollment</th>
            <th>Email</th>
            <th>Position</th>
            <th>Phone</th>
            <th>Address</th> -->
            <th>Department</th>
            <th></th>
        </tr>
    </thead>

    <tbody id="employee-datatable-body">
    </tbody>
</table>
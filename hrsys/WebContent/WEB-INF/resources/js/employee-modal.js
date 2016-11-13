function toggleFailAlert() {
    $(".alert-danger").show();
    $(".alert-success").hide();
}

function toggleSuccessAlert() {
    $(".alert-danger").hide();
    $(".alert-success").show()
}

$(document).ready(function() {
	var url;

    employeeModal = {
        url: "",
        _this: $("#employee-modal"),
        show: function(e) {
            // $("#employee-modal").modal();
            var purpose = $(e.relatedTarget).data("purpose");
            // populate employee data if editing
            if (purpose == "edit") {
                var employeeId = $(e.relatedTarget).data('employeeid');
                this.url = employeeInfoUrl + "/" + employeeId;
                employeeModal.fillInput(employeeId);
            } else if (purpose == "add"){
                this.url = employeeInfoUrl;
            } else {
                this.url = employeeInfoUrl + "/" + userEmployeeId;
                employeeModal.fillInput();
            }
        },
        hide: function(e) {
            $(".alert-danger").hide();
            $(".alert-success").hide();

            $("#employee-modal").find("form")[0].reset();
            $(".changed-input").removeClass("changed-input");
        },
        fillInput: function() {
            $.ajax({
                type: "GET",
                url: this.url,
            }).done(function(data) {
                if(data.employeeID == null || data.emloyeeID == '') {
                    alert("Employee entry no long exists");
                    $("#employee-datatable").DataTable().ajax.reload();
                }
                $.each(data, function(key, value){
                    $("#"+key).val(value);
                });
            }).fail(function(data) {
                alert("Employee entry no longer exists.");
            });
        }
    }

    // employee modal setup
    $(".alert-danger").hide();
    $(".alert-success").hide();

    $("#employee-modal input, #employee-modal select").change(function(e) {
        $(this).addClass("changed-input");
    })

    // populate department list
    $.ajax({
        type: "GET",
        url:"rest/department"
    }).done(function(data) {
        for (var key in data) {
            var option = $("<option></option>").text(data[key].departmentName);
            option.val(data[key].departmentID);

            $("#departmentID").append(option);
        }
    }).fail(function() {
        alert("Ajax failed to fetch data");
    });

    $("#employee-modal").on("show.bs.modal", function(e) {
        employeeModal.show(e);
    });

    $("#employee-modal").on("hidden.bs.modal", function(e){
        employeeModal.hide(e);
    });

    // datepicker setup
    $( "#birth, #enrollmentDate" ).datepicker({
        changeMonth: true,
        changeYear: true,
        maxDate: "+0D",
        dateFormat: "yy-mm-dd"
      });

    // format ssn input
    $('#ssn').keyup(function() {
        var val = this.value.replace(/\D/g, '');
        var newVal = '';
        if(val.length > 3) {
           this.value = val;
        }
        if((val.length > 3) && (val.length < 6)) {
           newVal += val.substr(0, 3) + '-';
           val = val.substr(3);
        }
        if (val.length > 5) {
           newVal += val.substr(0, 3) + '-';
           newVal += val.substr(3, 2) + '-';
           val = val.substr(5);
         }
        newVal += val;
        this.value = newVal;
      });
});


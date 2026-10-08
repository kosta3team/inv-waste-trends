document.querySelectorAll('.stockType').forEach(function(checkbox){

    checkbox.addEventListener('change', function(){

        document.querySelectorAll('.stockType').forEach(function(other){

            if(other !== checkbox){
                other.checked = false;
            }

        });

        const stockType = this.checked ? this.value : "";

        location.href =
            document.getElementById("contextPath").value
            + "/controller?cmd=inventoryList&stockType="
            + stockType;
			
			
    });

});
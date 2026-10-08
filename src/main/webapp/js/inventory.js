
document.querySelectorAll('.stockType').forEach(function (checkbox) {
    checkbox.addEventListener('change', function () {
        document.querySelectorAll('.stockType').forEach(function (other) {
            if (other !== checkbox) other.checked = false;
        });
        document.getElementById('searchForm').submit();
    });
});


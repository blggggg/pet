(function () {
    function bindPlaceholderEvents() {
        var buttons = document.querySelectorAll('.bv-mini-btn, .bv-grid-btn');
        for (var i = 0; i < buttons.length; i++) {
            buttons[i].addEventListener('click', function () {
                var text = this.innerText || this.textContent;
                if (text && text.indexOf('Save') >= 0) {
                    alert('当前为主页面演示版，后续可在这里接保存接口。');
                }
            });
        }
    }

    function bindCheckboxExclusive() {
        var rows = document.querySelectorAll('.bv-main-table tbody tr');
        for (var i = 0; i < rows.length; i++) {
            (function (row) {
                var inputs = row.querySelectorAll('td input[type="checkbox"]');
                if (inputs.length >= 4) {
                    var na = inputs[1];
                    var nt = inputs[2];
                    var nr = inputs[3];

                    function bindExclusive(target, others) {
                        target.addEventListener('change', function () {
                            if (target.checked) {
                                for (var j = 0; j < others.length; j++) {
                                    others[j].checked = false;
                                }
                            }
                        });
                    }

                    bindExclusive(na, [nt, nr]);
                    bindExclusive(nt, [na, nr]);
                    bindExclusive(nr, [na, nt]);
                }
            })(rows[i]);
        }
    }

    document.addEventListener('DOMContentLoaded', function () {
        bindPlaceholderEvents();
        bindCheckboxExclusive();
    });
})();

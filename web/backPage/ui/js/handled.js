$(function () {
    var data = [{
        uid: "7791",
        name: "keenleung1",
        age: "26",
        height: "165",
        description: "描述"
    },
        {
            uid: "7792",
            name: "keenleung2",
            age: "26",
            height: "165",
            description: "描述"
        },
        {
            uid: "7793",
            name: "keenleung3",
            age: "26",
            height: "165",
            description: "描述"
        },
        {
            uid: "7794",
            name: "keenleung4",
            age: "26",
            height: "165",
            description: "描述"
        },
        {
            uid: "7795",
            name: "keenleung5",
            age: "26",
            height: "165",
            description: "描述"
        },
    ];

    $('#table').bootstrapTable('load', data);

    var $result = $('#eventsResult');

    // 选择一行
    $('#table').on('click-row.bs.table', function (e, row, $element) {
        alert(JSON.stringify(row.uid));
    });
});

function actionFormatter(value, row, index) {
    return [
        "<button class='btn btn-primary like'>选取</button>",
        "<button class='btn btn-default unlike'>取消</button>",
    ].join('');
}

window.actionEvents = {
    'click .like': function (e, value, row, index) {
        alert(JSON.stringify(row));
        return false;
    },
    'click .unlike': function (e, value, row, index) {
        alert("unlike click");
        return false;
    },
};
$(function () {
    // insert left arrow in front of everything marked with arrow class
    $(".arrow").each(function () {
        $(this).prepend("&larr;&nbsp;");
    });
});
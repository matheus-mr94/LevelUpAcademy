$('.button-disable').click(function(){
    const button = $(this);
    const id = button.data('subcategoryId');

    $.post('/admin/subcategory/changeStatus/' + id, function (){
        $('#activeStatus_' + id).text("Inativa");
        button.remove();
    })
})

$('.btn-disable').click(function(){
    const button = $(this);
    const id = button.data('categoryId');

    $.post('/admin/category/changeStatus/' + id, function (){
       button.parent().siblings('.status').text('Inativa');
       button.remove();
    })
})
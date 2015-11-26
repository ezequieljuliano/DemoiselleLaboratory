var PessoaProxy = {
    url: "api/pessoas",
    inserir: function (_pessoa) {
        return $.ajax({
            type: "POST",
            url: this.url,
            data: JSON.stringify(_pessoa),
            contentType: "application/json"
        });
    }
};
function PbTableCtrl($scope) {
    
    $scope.downloadPDF = function() {
        
        const allContent = [];
        
        let row = [];
        
        $scope.properties.headers.forEach((element,index) => {
            const object = {text: element, alignment: 'center', style: {bold: true, fontSize: 13,color: 'black'}}
            row.push(object);
        });
    
        allContent.push(row);
        
        $scope.properties.content.forEach(element => {
            
           row = [];
            
          for (let key in element) {
            if(!element[key].includes('object:'))  {
                console.log(key);
                if(key !== 'attribute0') {
                    const object = {text: element[key], alignment: 'center' }
                    row.push(object);
                }else {
                    row.push(element[key]);
                }
            }
          }
          
          allContent.push(row);
        })
            
        var docDefinition = {
          content: [
            {
              table: {
                // headers are automatically repeated if the table spans over multiple pages
                // you can declare how many rows should be treated as headers
                headerRows: 1,
                widths: [ 300, 70, 70, 70 ],
        
                body: allContent,
              }
            }
          ]
        };
               
        pdfMake.createPdf(docDefinition).download("testes.pdf");   
            
            
    };
    
  this.isArray = Array.isArray;

  this.isClickable = function () {
    return $scope.properties.isBound('selectedRow');
  };

  this.selectRow = function (row) {
    if (this.isClickable()) {
      $scope.properties.selectedRow = row;
    }
  };

  this.isSelected = function(row) {
    return angular.equals(row, $scope.properties.selectedRow);
  }
}

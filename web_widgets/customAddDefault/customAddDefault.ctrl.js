function PbButtonCtrl($scope, $http, $location, $log, $window, localStorageService, modalService) {

  
  this.action = function action() {
    
    const currentItems = $scope.properties.targetArray;
      
    $scope.properties.targetArray = [...currentItems, ...$scope.properties.itemsToAdd];
      
//   for(let i = 0; i < $scope.properties.itemsToAdd; i++){
//         $scope.properties.targetArray.push($scope.properties.itemsToAdd(i));
//   }
   
   
   const Toast = Swal.mixin({
                          toast: true,
                          position: 'top-end',
                          showConfirmButton: false,
                          timer: 3000
                        })
    
    Toast.fire({
      type: 'success',
      title: $scope.properties.textMessage
    })
    
  }

}

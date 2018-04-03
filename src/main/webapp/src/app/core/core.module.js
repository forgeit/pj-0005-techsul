(function () {

	angular.module('app.core', [
		'ngRoute',
		'ui.bootstrap',
		'ui.select',
		'ngSanitize',
        'ngStorage',
		'ui.utils.masks',
		'angular-jwt',
        'core.utils',
		'core.auth'
	]);

})();
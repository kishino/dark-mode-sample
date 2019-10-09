var exec = require('cordova/exec');

var DarkMode = function () {};

DarkMode.prototype.init = function () {
  var that = this
  var success = function (theme) {
    if (that.onThemeChanged) {
      that.onThemeChanged(theme)
    }
  }
  var fail = function () {}
  cordova.exec(
      success,
      fail,
      'DarkMode',
      'init',
      []
  )
}

DarkMode.prototype.getTheme = function () {
  return new Promise(function(resolve, reject) {
    cordova.exec(
        resolve,
        reject,
        'DarkMode',
        'getTheme',
        []
    )
  })
}

DarkMode.prototype.setOnThemeChanged = function (onThemeChanged) {
  this.onThemeChanged = onThemeChanged
}

module.exports = new DarkMode()

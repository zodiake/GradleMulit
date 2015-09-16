module.exports = function (grunt) {
  // Project configuration.
  grunt.initConfig({
    config: {
      src: 'src/*.html'
      dist: 'dist/'
    },
    'string-replace': {
      dist: {
        files: {
          '<%= config.dist %>': '<%= config.src %>'
        },
        options: {
          replacements: [{
            pattern: /<!-- @import (.*?) -->/ig,
            replacement: function (match, p1) {
              return grunt.file.read(grunt.config.get('config.dist') + p1);
            }
          }]
        }
      }
    }
  });

  // These plugins provide necessary tasks.
  grunt.loadNpmTasks('grunt-string-replace');

  // Default task.
  grunt.registerTask('default', ['string-replace']);
};
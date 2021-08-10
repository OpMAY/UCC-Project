$(function() {
  'use strict';

  $('#tags').tagsInput({
    'width': '100%',
    'height': '75%',
    'interactive': true,
    'defaultText': 'Add More',
    'removeWithBackspace': true,
    'minChars': 0,
    'maxChars': 20,
    'placeholderColor': '#666666'
  });
  $('#loudsourcing-hashtag').tagsInput({
    'width': '100%',
    'height': '65px',
    'interactive': true,
    'defaultText': '',
    'removeWithBackspace': true,
    'minChars': 0,
    'maxChars': 5,
    'placeholderColor': '#666666'
  });
});
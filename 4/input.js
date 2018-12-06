regExps = {
"exercise_1": /Internet|Words|Or/,
"exercise_2": /088[1-7]{7}/,
"exercise_3": /[^01]+/,
"exercise_4": /^[a-zA-Z]([._]|[a-zA-Z0-9]){2,20}$/,
"exercise_5": /(999|1[0-4][0-9]{2}|1500)/,
"exercise_6": /class=['"].*['"]/
};
cssSelectors = {
"exercise_1": "item > java.class1",
"exercise_2": "different java.diffClass",
"exercise_3": "java > tag:only-of-type",
"exercise_4": "#someId ~ item",
"exercise_5": "tag > java:nth-of-type(2)",
"exercise_6": "item.class1 > item.class2, item.class2 item:only-child",
"exercise_7": "#diffId2 java:last-child",
"exercise_8": "#someId"
};

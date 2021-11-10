module.exports = {
    verbose: true,
    moduleNameMapper: {
        "^@/(.*)$": "<rootDir>/src/$1"
    },
    moduleFileExtensions: [
        "js",
        "json",
        "vue"
    ],
    transform: {
        ".*\\.(vue)$": "vue-jest",
        ".*\\.(js)$": "babel-jest"
    },
    testEnvironment: "jsdom",
};
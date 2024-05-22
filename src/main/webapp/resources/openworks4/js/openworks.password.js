/**
 * PBKDF2 방식의 AES128 암호화
 */
var opEncrypt = function (plainText) {
    // passwd-commons-config.xml의 설정값과 맞춰야함
    var PASS_SALT = "97f2fde29cd4493f199c2f3e9b7df120";
    var PASS_IV = "4c1f89c42e9f06036385e90aadd7389f";
    var PASS_PHRASE = "v4.0";
    var PASS_ITERATION = 1000;
    var PASS_KEY_SIZE = 128;

    // PBKDF2 키 생성
    var key128Bits100Iterations = CryptoJS.PBKDF2(PASS_PHRASE, CryptoJS.enc.Hex.parse(PASS_SALT), {keySize: parseInt(PASS_KEY_SIZE)/32, iterations: parseInt(PASS_ITERATION)});
    var encrypted = CryptoJS.AES.encrypt(plainText, key128Bits100Iterations, {iv: CryptoJS.enc.Hex.parse(PASS_IV)});

    return encrypted.toString();
};
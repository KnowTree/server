import Validate from "validate.js";

export default class DataValidator {
    static demandString(data) {
        if (!Validate.isString(data)) throw data + " is not string";
    }

    static demandObject(data) {
        if (!Validate.isObject(data)) throw data + " is not object";
    }

    static demandObjectHasField(data, fields) {
        this.demandObject(data);
        for (let field of fields) {
            if (data.hasOwnProperty(field)) throw data + " does not have field " + field;
        }
    }
}


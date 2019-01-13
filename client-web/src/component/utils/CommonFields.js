import DataValidator from "./DataValidator";

export class CommonFields {
    static get NAME() {
        return 'name';
    }
    static get HEADER() {
        return 'header';
    }
    static get BODY() {
        return 'body';
    }
    static get FOOTER() {
        return 'footer';
    }

    static get INPUT_TYPE() {
        return 'input_type';
    }

    static get INPUT_NAME() {
        return 'input_name';
    }

    static get LABEL() {
        return 'label';
    }

    static get MESSAGE() {
        return 'message';
    }

    static get FORM_STYLE() {
        return 'form_style';
    }

    static get ITEMS() {
        return 'items';
    }

    static copyTo(ori, dest, fields) {
        for (let field of fields) {
            DataValidator.demandString(field);
            dest[field] = ori[field];
        }
    }
}
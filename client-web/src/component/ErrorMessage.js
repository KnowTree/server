import React from 'react';
import Component from "./Component";
import {CommonFields} from "./utils/CommonFields";

export default class ErrorMessage extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <p>
                {this.getProperty(CommonFields.MESSAGE)}
            </p>
        )
    }
}
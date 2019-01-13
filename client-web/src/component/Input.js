import React from 'react';
import Component from "./Component";
import {CommonFields} from "./utils/CommonFields";
import ErrorMessage from "./ErrorMessage";

class Input extends Component {
    static TEXT_INPUT_TYPE = "text";
    constructor(props) {
        super(props);
        this.setState({
            enable : true,
            error : null
        })
    }

    render() {
        return (
            <div>
                <label>{this.getProperty(CommonFields.LABEL)}</label>
                <input type={this.getProperty(CommonFields.INPUT_TYPE)} name={this.getProperty(CommonFields.INPUT_NAME)}>
                </input>
                {this.state.error !== null &&
                    <ErrorMessage message={this.state.error}/>
                }
            </div>
        )
    }
}
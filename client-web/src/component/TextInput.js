import React from 'react';
import Component from "./Component";

export default class TextInput extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="input-group mb-3">
                <input type="text" className="form-control" placeholder={this.props.placeholder} aria-label={this.props.label}
                       aria-describedby="basic-addon1"/>
            </div>
        )
    }
}
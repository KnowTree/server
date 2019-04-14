import React from 'react';
import Component from "./Component";

export default class OutlineButton extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <button type="button" className="btn btn-outline-primary">{this.props.label}</button>
        )
    }
}
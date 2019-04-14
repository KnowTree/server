import React from 'react';
import Component from "./Component";
import TextInput from "./TextInput";

export default class DataBrowser extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <TextInput name="search" label="Search"/>
        )
    }
}
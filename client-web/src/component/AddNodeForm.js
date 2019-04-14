import React from 'react';
import Component from "./Component";
import Input from './Input';
import TextInput from "./TextInput";

export default class AddNodeForm extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <TextInput name="title" label="Title"/>
        )
    }
}
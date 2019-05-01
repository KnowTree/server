import React from 'react';

class Form extends React.Component {
    constructor() {
        super();
        this.state = {
            isNew : true,
            data : {},
            error : null
        };
        this.inputs = [];
        this.submit = this.submit.bind(this);

    }

    componentDidMount() {
        this.doFetch();
    }

    doFetch() {

    }

    render() {
        return (<div>
          Pls extend
      </div>)
    }

    data(values) {
        if (!!values) {
            this.inputs.forEach((input, index) => {
                input.applyScope(values);
            })
        } else {
            let result = {};
            for (let input of this.inputs) {
                result[input.getName()] = input.getValue();
            }
            return result;
        }
    }

    /**
     *
     * @param error back from server {message : "...", detail : {inputName : message} }
     * @returns {null}
     */
    error(error) {
        if (!!error) {
            this.state.error = error.message; //general error
            if (error.detail) { //detail on each input if have
                this.inputs.forEach((input, index) => {
                    input.error(error.detail);
                })
            }
        } else {
            return this.state.error;
        }
    }


    addInput(input) {
        this.inputs.push(input);
    }

    /**
     * This ovrerriding prevents form to re-render all input on update data.
     * @param state
     */
    setState(state) {
        console.log("Dont setState() directly on form. Should call data(values)");
    }

    submit() {
        this.doSubmit();
        this.onSubmitted();

    }

    doSubmit() {

    }

    onSubmitted() {

    }
}

export default Form;
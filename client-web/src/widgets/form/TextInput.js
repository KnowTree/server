import React from "react";

class TextInput extends React.Component {
    constructor() {
        super();
        this.state = {
            value : null,
            enable : true,
            error : null,
        };

        this.onChange = this.onChange.bind(this);
    }
    componentDidMount() {
        this.form = this.props ? this.props.form : null;
        this.form.addInput(this);
    }
    render() {
        return (
            <div>
                <label>{this.props.label}</label>
                {this.state.enable ?
                    (<input onChange={this.onChange} value={this.state.value} name={this.props.name}/>) :
                    (<p>{this.state.value}</p>)
                }
                {
                    this.state.error ?
                        <p>{this.state.error}</p>
                        : ''
                }
            </div>
        )
    }

    getName() {
        return this.props.name;
    }

    getValue() {
        return this.state.value;
    }

    onChange(e) {
        this.setState({
            value : e.target.value
        })
    }

    enable(value) {
        if (!!value) {
            this.setState({
                enable : value
            })
        } else {
            return this.state.enable
        }
    }

    applyScope(scope) {
        const name = this.props.name;
        const value = scope[name];
        this.setState({
            value : value
        });
        if (this.form) {
            //set directly to avoid rerender entire form
            this.form.state.data[name] = value;
        }
    }

    /**
     *
     * @param error If string, set directly to state.error. If (object) error.detail, filter error msg from it
     * @returns {null}
     */
    error(error) {
        if (!!error) {
            if (typeof error === "string") {
                this.setState({
                    error: error
                });
            } else if (typeof error === 'object') {
                const msg = error[this.props.name];
                this.setState({
                    error : msg
                });
            }
        } else {
            return this.state.error;
        }
    }
}

export default TextInput;
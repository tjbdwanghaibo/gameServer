package main

import (
	"flag"
	"fmt"
	. "fmt"
	"io/ioutil"
	"os"
	"regexp"
)

var REPLACE []string = []string{
	`public final class Msg {`, "@SuppressWarnings({\"unused\"})\npublic final class Msg {",
	`\/\*(\s|.)*?\*\/\n`, ""}

/*	getFuncReg("public", "getDefaultInstanceForType", 1), ``,
	getFuncReg("public", "getDescriptor", 1), ``,
	getFuncReg("protected", "internalGetFieldAccessorTable", 1), ``,
	getFuncReg("public", "getDescriptorForType", 1), ``}
*/
func getFuncReg(level string, funcName string, depth int) string {
	strReg := `\b%s(\s)+[\s|\w|\.]+(\s)+%s\((\s|\w|\.)*?\)\s*\{([^\}]*\}){%d}`
	strReg = fmt.Sprintf(strReg, level, funcName, depth)
	return strReg
}

func main() {
	flag.Parse()
	file := ""
	if flag.NArg() >= 1 {
		file = flag.Arg(0)
	}
	byteContent, err := ioutil.ReadFile(file)
	if err != nil {
		Println("Can't open file:", file)
		return
	}
	content := string(byteContent)

	for i, v := range REPLACE {
		if i%2 == 0 {
			v2 := REPLACE[i+1]
			reg := regexp.MustCompile(v)
			content = reg.ReplaceAllString(content, v2)
		}
	}
	//content = strings.Replace(content, SRC_STR, DEST_STR, -1)
	ioutil.WriteFile(file, []byte(content), os.ModeAppend)
	Println("replace ok.")
}
